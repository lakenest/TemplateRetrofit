package com.example.nest101.ui.participants


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nest101.data.model.ParticipantsModel
import com.example.nest101.domain.UseCaseGetParticipantsId
import kotlinx.coroutines.launch


class ParticipantsViewModel: ViewModel() {

    var participantsModel = MutableLiveData<ParticipantsModel?>()

    val useCaseGetParticipantsId = UseCaseGetParticipantsId()

    fun onCreate(panstId:Int){
        viewModelScope.launch {
            val result = useCaseGetParticipantsId(panstId)
            participantsModel.postValue(result)
        }

    }

}