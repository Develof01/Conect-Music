package com.example.conct_music.di

import android.media.MediaPlayer
import androidx.room.Room
import com.example.conct_music.BuildConfig
import com.example.data.database.AppDatabase
import com.example.conct_music.utils.AssetsPropertyReader
import com.example.conct_music.utils.Const
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val mRoomModule by lazy {
    module {
        factory {
            Room.databaseBuilder(androidApplication(), AppDatabase::class.java, Const.DB_NAME).allowMainThreadQueries()
                .build()
        }
    }
}


val mNetworkModule by lazy {
    module {
        factory {
            createNetworkClient(
                AssetsPropertyReader(androidContext()).getProperty()[BuildConfig.ITUNES_URL_BASE].toString(),
                15
            )
        }
    }
}


val mAudio by lazy {
    module {
        factory {
            MediaPlayer()
        }
    }
}
