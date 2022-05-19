package com.example.crud3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class table : AppCompatActivity() {

    val instanceFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        var btConsultar = findViewById<Button>(R.id.btConsultar)
        var tvConsulta = findViewById<TextView>(R.id.tvConsulta)



        btConsultar.setOnClickListener {
            var datos = ""
            instanceFirestore.collection("prueba")
                .get().addOnSuccessListener { resultado ->
                    for (documento in resultado) {
                        datos += "${documento.id}: ${documento.data}\n"
                    }
                    tvConsulta.text = datos
                }.addOnFailureListener { _ ->
                    tvConsulta.text = "No se conecto"

                }
        }


    }
}