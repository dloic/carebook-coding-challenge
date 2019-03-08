package com.jrd.carebook.activities

import android.content.Context
import android.content.Intent
import com.jrd.carebook.model.Constants

class Router(private val context: Context) {
    fun navigateHome() {
        val intent = Intent(context, Home::class.java)
        intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToPreview(patientId: String) {
        val intent = Intent(context, PatientPreview::class.java)
        intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(Constants.PATIENT_ID_EXTRA, patientId)
        context.startActivity(intent)
    }
}