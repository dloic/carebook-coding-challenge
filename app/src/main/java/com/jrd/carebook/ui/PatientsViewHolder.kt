package com.jrd.carebook.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jrd.carebook.R
import com.jrd.carebook.activities.Router
import com.jrd.carebook.dagger2.MyApplication
import com.jrd.carebook.model.Patient
import kotlinx.android.synthetic.main.item_bundle_response.view.*
import javax.inject.Inject

class PatientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @Inject
    lateinit var router: Router

    init {
        (itemView.context.applicationContext as MyApplication).getAppComponent()?.inject(this)
    }

    fun bind(patient: Patient) = with(itemView) {
        var display = ""
        if (!patient.name.isNullOrEmpty()) {
            if (!patient.name.first().given.isNullOrEmpty()) {
                display += patient.name.first().given.first()
            }
            if (!patient.name.first().family.isNullOrEmpty()) {
                display += " ${patient.name.first().family}"
            }
        }
        if (display.isNotEmpty()) {
            display = itemView.context.getString(R.string.patient_name, display)
        }
        display += " ${itemView.context.getString(R.string.patient_id, patient.id)}"
        textview_name.text = display
        setOnClickListener { router.navigateToPreview(patient.id) }
    }
}