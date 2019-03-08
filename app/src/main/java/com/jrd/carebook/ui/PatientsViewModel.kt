package com.jrd.carebook.ui

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.jrd.carebook.dagger2.MyApplication
import com.jrd.carebook.data.PageKeyedBundleDataSource
import com.jrd.carebook.data.Repository
import com.jrd.carebook.model.Patient
import com.jrd.carebook.model.State
import javax.inject.Inject

class PatientsViewModel(application: Application) : ViewModel() {
    @Inject
    lateinit var repository: Repository

    lateinit var retry: () -> Unit
    lateinit var patientList: LiveData<PagedList<Patient>>
    lateinit var state: LiveData<State>

    init {
        (application as MyApplication).getAppComponent()?.inject(this)
    }

    fun addPatients() {
        val mainResponse = repository.getMainResponse()
        patientList = mainResponse.pagedList
        state = Transformations.switchMap<PageKeyedBundleDataSource, State>(
            mainResponse.sourceLiveData,
            PageKeyedBundleDataSource::state
        )
        retry = {
            mainResponse.sourceLiveData.value?.retryAllFailed()
        }
    }
}