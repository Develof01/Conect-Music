package com.example.conct_music.di

import androidx.room.Room
import com.example.conct_music.BuildConfig
import com.example.data.database.AppDatabase
import com.example.conct_music.utils.AssetsPropertyReader
import com.example.conct_music.utils.Const
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
val mNetworkModule = module {
    single(named(RETROFIT_INSTANCE)) {
        createNetworkClient(
            AssetsPropertyReader(androidContext()).getProperty()[BuildConfig.ITUNES_URL_BASE].toString(),
            15
        )
    }
    single(named(API)) { (get() as Retrofit).create(WsInteractor::class.java) }
} */


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


private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val DATABASE = "Room"