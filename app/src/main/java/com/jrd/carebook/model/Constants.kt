package com.jrd.carebook.model

object Constants {
    const val API_BASE_URL = "https://hapi.fhir.org/baseDstu3/"
    const val API_SHORT_UNSECURED_URL = "http://hapi.fhir.org/baseDstu3"
    //By tweaking this parameter we can visualize the beauty of Google's PageKeyedDataSource pagination
    const val PAGE_SIZE = 50
    const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
    const val CACHE_SIZE = 10 * 1024 * 1024 // 10 MiB
    const val TEST_JSON_FOLDER = "../app/src/main/assets/mockedResponse.json"
    const val NEXT_RELATION = "next"
}

enum class State {
    LOADING, LOADED, ERROR
}