package com.sourabh.imagedownloader.data.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.Coil
import coil.request.LoadRequest
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ImageSaverService(
    private val context: Context,
    private val title: String,
    private val imageUrlList: List<String>
) {

    private lateinit var fileOutputStream: FileOutputStream

    fun saveToInternalStorage() {

        for ((index, url) in imageUrlList.withIndex()) {

            val request = LoadRequest.Builder(context)
                .data(url)
                .target { drawable ->
                    val bitmap = (drawable as BitmapDrawable).bitmap
                    writeToDownloads(bitmap, index)
                }
                .build()
            Coil.imageLoader(context).execute(request)
        }
    }

    private fun writeToDownloads(bitmap: Bitmap, id: Int) {

        try {
            fileOutputStream =
                context.openFileOutput("${title}_${id}.jpg", Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun closeFileOutputStream() = fileOutputStream.close()
}