package com.jrd.carebook.data

import com.jrd.carebook.model.Bundle
import com.jrd.carebook.model.PatientDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {
    /**
     * The main call to the server which returns a bundle containing all the entries
     */
    @GET("Patient?_pretty=true/")
    fun getBundle(): Call<Bundle>

    /**
     * The call that are performed after the root bundle is retrieved to get the next one
     */
    @POST("{next_url}")
    fun getNextBundle(@Path("next_url") next_url: String): Call<Bundle>

    /**
     * The call responsible of getting the patient's full information
     */
    @GET("Patient/{patient_id}")
    fun getPatient(@Path("patient_id") patient_id: String): Call<PatientDetails>
}

/**
 * A helper data class that is responsible for specifying the next page that is to be fetched during pagination
 */
data class ApiParams(var nextPage: String)

