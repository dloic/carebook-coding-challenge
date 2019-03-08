package com.jrd.carebook.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.os.Handler
import android.os.Looper
import com.jrd.carebook.model.Bundle
import com.jrd.carebook.model.Constants
import com.jrd.carebook.model.Patient
import com.jrd.carebook.model.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageKeyedBundleDataSource(private val apiClient: ApiClient) : PageKeyedDataSource<ApiParams, Patient>() {

    private var mainHandler = Handler(Looper.getMainLooper())

    val state = MutableLiveData<State>()
    // keep a function reference for the retry event
    private var retry: (() -> Unit)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }

    /**
     * The first fetch that is to be performed by our adapter
     */
    override fun loadInitial(params: LoadInitialParams<ApiParams>, callback: LoadInitialCallback<ApiParams, Patient>) {
        state.postValue(State.LOADING)
        mainHandler.post {
            apiClient.getBundle().enqueue(object : Callback<Bundle> {
                override fun onResponse(call: Call<Bundle>, response: Response<Bundle>) {
                    val rootBundle = response.body()
                    if (rootBundle == null) {
                        onFailure(call, Throwable())
                    } else {
                        state.postValue(State.LOADED)
                        var nextPageUrl = rootBundle.link.find { it.relation == Constants.NEXT_RELATION }?.url
                        if (nextPageUrl == null) {
                            nextPageUrl = ""
                        }
                        callback.onResult(getPatientsFromBundle(rootBundle), null, ApiParams(nextPageUrl))
                    }
                }

                override fun onFailure(call: Call<Bundle>, t: Throwable) {
                    retry = {
                        loadInitial(params, callback)
                    }
                    state.postValue(State.ERROR)
                }
            })
        }
    }

    /**
     * The next fetch performed when we reach the end of the page
     */
    override fun loadAfter(params: LoadParams<ApiParams>, callback: LoadCallback<ApiParams, Patient>) {
        state.postValue(State.LOADING)
        mainHandler.post {
            //removes the api from the url since retrofit already handles it
            val nextParam = params.key.nextPage.replace(Constants.API_SHORT_UNSECURED_URL, "")
            apiClient.getNextBundle(nextParam).enqueue(object : Callback<Bundle> {
                override fun onResponse(call: Call<Bundle>, response: Response<Bundle>) {
                    val rootBundle = response.body()
                    if (rootBundle == null) {
                        onFailure(call, Throwable())
                    } else {
                        val nextPageUrl = rootBundle.link.find { it.relation == Constants.NEXT_RELATION }?.url
                        if (nextPageUrl == null) {
                            onFailure(call, Throwable())
                        } else {
                            state.postValue(State.LOADED)
                            callback.onResult(getPatientsFromBundle(rootBundle), ApiParams(nextPageUrl))
                        }
                    }
                }

                override fun onFailure(call: Call<Bundle>, t: Throwable) {
                    retry = {
                        loadAfter(params, callback)
                    }
                    state.postValue(State.ERROR)
                }
            })
        }
    }

    override fun loadBefore(params: LoadParams<ApiParams>, callback: LoadCallback<ApiParams, Patient>) {
        // ignored, since we only ever append to our initial load
    }

    /**
     * Just a helper method to avoid duplicate code
     */
    private fun getPatientsFromBundle(bundle: Bundle): ArrayList<Patient> {
        val patients = ArrayList<Patient>()
        bundle.entry.forEach {
            patients.add(it.resource)
        }
        return patients
    }
}