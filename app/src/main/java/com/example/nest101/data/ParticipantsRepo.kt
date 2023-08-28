package com.example.nest101.data

import com.example.nest101.data.model.ParticipantsModel
import com.example.nest101.data.network.ParticipantsService

class ParticipantsRepo{

    private val api = ParticipantsService()

    suspend fun getParticipantsId(participantsId:Int): ParticipantsModel? {
        val response = api.callParticipants(participantsId)
        return response
    }

}