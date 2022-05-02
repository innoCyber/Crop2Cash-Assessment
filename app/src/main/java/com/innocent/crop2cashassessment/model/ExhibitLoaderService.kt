package com.innocent.crop2cashassessment.model

import retrofit2.http.GET

interface ExhibitLoaderService {
    @GET("Reyst/exhibit_db/list")
    suspend fun getExhibitList(): List<Exhibit>
}