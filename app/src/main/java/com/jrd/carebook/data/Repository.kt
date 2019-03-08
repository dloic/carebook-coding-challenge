package com.jrd.carebook.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.jrd.carebook.dagger2.MyApplication
import com.jrd.carebook.model.Constants
import com.jrd.carebook.model.Patient
import com.jrd.carebook.model.PatientDetails
import com.jrd.carebook.model.State
import dagger.Module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


@Module
class Repository(application: MyApplication) {
    @Inject
    lateinit var retrofit: Retrofit
    private var apiClient: ApiClient

    init {
        application.getAppComponent()?.inject(this)
        apiClient = retrofit.create(ApiClient::class.java)
    }

    /**
     * Just a helper data class to transport the [pagedList] and [sourceLiveData] back to the view model
     */
    data class LiveDataBundle(val pagedList: LiveData<PagedList<Patient>>, val sourceLiveData: MutableLiveData<PageKeyedBundleDataSource>)

    /**
     * This method is responsible for creating a PageKeyedBundleDataSource which will handle paginating the data
     */
    fun getMainResponse(): LiveDataBundle {
        val sourceFactory = BundleDataSourceFactory(apiClient)
        val livePagedList = LivePagedListBuilder(sourceFactory, Constants.PAGE_SIZE).build()
        return LiveDataBundle(livePagedList, sourceFactory.sourceLiveData)
    }

    /**
     * This is the method responsible for fetching the full patient's data from our endpoint
     *
     * @param: the patient Id
     * @param: A callback triggered once the full details are retrieved
     */
    fun getFullDetails(patientId: String, callback: (PatientDetails) -> Unit) {
        val patientDetailCall = apiClient.getPatient(patientId)

        patientDetailCall.enqueue(object : Callback<PatientDetails> {
            override fun onResponse(call: Call<PatientDetails>, response: Response<PatientDetails>) {
                response.body()?.let { callback.invoke(it) }
            }

            override fun onFailure(call: Call<PatientDetails>, t: Throwable) {
                Log.d("Fail", "Fail")
            }
        })
    }
}