package com.example.conct_music.di


import com.example.data.interactor.WsInteractor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String,  timeout: Long) = retrofitClient(baseUrl, httpClient(timeout))

private fun httpClient(timeout: Long): OkHttpClient {
    return  OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): WsInteractor =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(WsInteractor::class.java)