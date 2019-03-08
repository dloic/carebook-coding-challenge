package com.jrd.carebook.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.jrd.carebook.model.Patient

/**
 * The factory responsible of creating a PageKeyedBundleDataSource which will handle the pagination logic
 */
class BundleDataSourceFactory(private val apiClient: ApiClient) :
    DataSource.Factory<ApiParams, Patient>() {
    val sourceLiveData = MutableLiveData<PageKeyedBundleDataSource>()

    override fun create(): DataSource<ApiParams, Patient> {
        val source = PageKeyedBundleDataSource(apiClient)
        sourceLiveData.postValue(source)
        return source
    }
}