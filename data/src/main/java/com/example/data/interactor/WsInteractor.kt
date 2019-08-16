package com.example.data.interactor

import com.example.domian.entities.MusicTrack
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WsInteractor {

    @FormUrlEncoded
    @POST("/search")
    fun getSingers(@Field("term")term: String, @Field("entity")entity: String): Deferred<Response<MusicTrack>>

}