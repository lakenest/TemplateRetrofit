package com.example.nest101.ui.participants

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nest101.databinding.ActivityParticipantsBinding

class ParticipantsActivity : AppCompatActivity() {
    lateinit var binding:ActivityParticipantsBinding
    private val participantsViewModel:ParticipantsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pantsId= 1
        val btnConsultar = binding.btnConsultar
        btnConsultar.setOnClickListener {
            participantsViewModel.onCreate(pantsId)
        }

        participantsViewModel.participantsModel.observe(this){ pantsModel ->
            if (pantsModel!=null){

                with(binding){
                    etActivity.setText(pantsModel.activity)
                    etType.setText(pantsModel.type)
                    etParticipants.setText(pantsModel.participants.toString())
                    etPrice.setText(pantsModel.price.toString())
                    etLink.setText(pantsModel.link)
                    etKey.setText(pantsModel.key)
                    etAccesibility.setText(pantsModel.accessibility.toString())



                }
            }
        }



    }
}