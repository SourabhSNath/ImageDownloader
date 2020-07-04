package com.sourabh.imagedownloader.data.remote

import org.jsoup.Jsoup

class PageParserService(private val url: String) {

    private val doc by lazy {
        Jsoup.connect(url).get()
    }

    /**
     * Title of the web page shall be used as the folder name
     */
    val title: String = doc.title()

    suspend fun getAllImages(): List<String> {

        val list = ArrayList<String>()
        doc.getElementsByTag("img").forEach {
            val url = it.attr("src")
            list.add(url)
        }

        return if (list.isNotEmpty()) {
            list
        } else {
            throw NullPointerException()
        }
    }
}