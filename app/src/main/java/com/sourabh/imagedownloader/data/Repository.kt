package com.sourabh.imagedownloader.data

import com.sourabh.imagedownloader.data.local.ImageSaverService
import com.sourabh.imagedownloader.data.remote.PageParserService

class Repository(
    private val pageParserService: PageParserService,
    private val imageSaverService: ImageSaverService
) {

    val getPageTitle = pageParserService.title

    suspend fun getAllImages() = try {
        pageParserService.getAllImages()
    } catch (npe: NullPointerException) {
        emptyList<String>()
    }

    fun saveImage() = imageSaverService.saveToInternalStorage()

    fun closeFileOutputStream() = imageSaverService.closeFileOutputStream()
}