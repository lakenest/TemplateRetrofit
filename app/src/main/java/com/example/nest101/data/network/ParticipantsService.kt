package com.example.nest101.data.network

import com.example.nest101.core.RetrofitHelper
import com.example.nest101.data.model.ParticipantsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParticipantsService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun callParticipants(participantsId:Int): ParticipantsModel? {
        return withContext(Dispatchers.IO){
            val response= retrofit.create(IParticipantsApiClient::class.java).getParticipants(participantsId)
            response.body()
        }
    }

}