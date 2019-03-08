package com.jrd.carebook.model

/**
 * The initial [Bundle] class, it is the root JSON Object
 */
data class Bundle(val id: String, val entry: List<Resource>, val link: List<Relation>)

/**
 * The resource containing patients information and the url to the full information of the given patient
 */
data class Resource(val resource: Patient, val fullUrl: String)

/**
 * The patients' information
 */
data class Patient(val id: String, val gender: String, val name: List<PatientName>)

/**
 * The patients' name
 */
data class PatientName(val family: String, val given: List<String>) {
    override fun toString(): String {
        return "${given.first()} $family"
    }
}

/**
 * The relation containing the url to the next [Bundle]
 */
data class Relation(val relation: String, val url: String)

/**
 * The patients' full information
 */
data class PatientDetails(
    val id: String?,
    val birthDate: String?,
    val gender: String?,
    val deceased: Boolean?,
    val active: Boolean?,
    val name: List<PatientName>?
)