package com.example.conct_music.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.util.*

class AssetsPropertyReader(var context: Context) {

    private var properties: Properties = Properties()


    fun getProperty(): Properties {
        try {
            val assetManager: AssetManager = context.assets
            val inputStream: InputStream = assetManager.open("app.properties")
            properties.load(inputStream)

        } catch (e: IOException) {
            Log.e("PropertiesReader", e.message)
        }

        return properties
    }

}