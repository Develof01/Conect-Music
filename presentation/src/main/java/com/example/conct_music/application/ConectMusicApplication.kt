package com.example.conct_music.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.conct_music.di.mAudio
import com.example.conct_music.di.mNetworkModule
import com.example.conct_music.di.mRoomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ConectMusicApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        startKoin {
            androidLogger()
            androidContext(applicationContext)
            loadKoinModules(listOf(mRoomModule, mNetworkModule, mAudio))
        }

    }

}