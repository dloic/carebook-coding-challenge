package com.jrd.carebook.activities

import android.os.Bundle
import com.jrd.carebook.R
import com.jrd.carebook.dagger2.MyApplication
import com.jrd.carebook.data.Repository
import com.jrd.carebook.model.Constants
import kotlinx.android.synthetic.main.activity_patient_preview.*
import javax.inject.Inject

class PatientPreview : BaseActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_preview)
        (application as MyApplication).getAppComponent()?.inject(this)
        val patientId = intent.getStringExtra(Constants.PATIENT_ID_EXTRA)
        repository.getFullDetails(patientId) {
            val id = it.id ?: getString(R.string.not_available)
            val birthDate = it.birthDate ?: getString(R.string.not_available)
            val gender = it.gender ?: getString(R.string.not_available)
            val deceased = it.deceased ?: getString(R.string.not_available)
            val active = it.active ?: getString(R.string.not_available)
            val name = it.name ?: getString(R.string.not_available)
            textview_patient_details.text =
                getString(R.string.patient_full_details, name, id, birthDate, gender, deceased, active)
        }
    }

}