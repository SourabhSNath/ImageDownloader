package com.sourabh.imagedownloader

import com.sourabh.imagedownloader.data.remote.PageParserService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private const val URL = "https://unsplash.com/wallpapers"

class ParserUnitTest {

    private lateinit var pageParserService: PageParserService

    @Before
    fun setUp() {
        pageParserService = PageParserService(URL)
    }

    @Test
    fun checkJsoupTitle() {
        Assert.assertNotNull(pageParserService.title)
    }

    @Test(expected = NullPointerException::class)
    fun whenLoadingImages_checkIfListIsEmpty() {

        // fonts.google.com doesn't have any images
        val p = PageParserService("https://fonts.google.com/?query=roboto")
        runBlocking { p.getAllImages() }
    }

    @Test
    fun whenLoadingImages_checkIfImageURLExists() {
        runBlocking {

            println(pageParserService.getAllImages().size)
            Assert.assertNotNull(pageParserService.getAllImages()[0])

        }

    }

}