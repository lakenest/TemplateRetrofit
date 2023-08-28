package com.example.nest101.domain

import com.example.nest101.data.ParticipantsRepo
import com.example.nest101.data.model.ParticipantsModel

class UseCaseGetParticipantsId {
    private val participantsRepo = ParticipantsRepo()

    suspend operator fun invoke(pantsId:Int): ParticipantsModel? {
        return participantsRepo.getParticipantsId(pantsId)
    }

}