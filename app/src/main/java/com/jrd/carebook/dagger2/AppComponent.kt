package com.jrd.carebook.dagger2

import com.jrd.carebook.activities.Launcher
import com.jrd.carebook.activities.PatientPreview
import com.jrd.carebook.data.Repository
import com.jrd.carebook.ui.PatientsViewHolder
import com.jrd.carebook.ui.PatientsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {
    fun inject(repository: Repository)
    fun inject(viewModel: PatientsViewModel)
    fun inject(activity: Launcher)
    fun inject(activity: PatientPreview)
    fun inject(patientsViewHolder: PatientsViewHolder)
}