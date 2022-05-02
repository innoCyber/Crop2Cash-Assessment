package com.innocent.crop2cashassessment.model

interface ExhibitsLoader {
    suspend fun getExhibitList(): List<Exhibit>
}