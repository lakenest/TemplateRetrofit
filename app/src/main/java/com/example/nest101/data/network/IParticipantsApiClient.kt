package com.example.nest101.data.network

import com.example.nest101.data.model.ParticipantsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IParticipantsApiClient {
    @GET("activity")
    suspend fun getParticipants(@Query("participants") participants:Int): Response<ParticipantsModel>
}