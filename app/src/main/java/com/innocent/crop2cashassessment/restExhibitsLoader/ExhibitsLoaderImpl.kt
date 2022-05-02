package com.innocent.crop2cashassessment.restExhibitsLoader

import com.innocent.crop2cashassessment.model.Exhibit
import com.innocent.crop2cashassessment.model.ExhibitLoaderService
import com.innocent.crop2cashassessment.model.ExhibitsLoader
import javax.inject.Inject

class ExhibitsLoaderImpl @Inject constructor(
    private val exhibitLoaderService: ExhibitLoaderService
) : ExhibitsLoader {
    override suspend fun getExhibitList(): List<Exhibit> {
        return exhibitLoaderService.getExhibitList().map {
            with(it){
                Exhibit(title = title, images = images)
            }
        }
    }
}