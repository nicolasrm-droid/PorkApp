package com.example.crud3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    val instanceFirestore = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btir = findViewById<Button>(R.id.btir)
        var btnsubmit = findViewById<Button>(R.id.submit)
        var etnameañadir = findViewById<EditText>(R.id.etnameañadir)
        var etConsultaañadir = findViewById<EditText>(R.id.etConsultaañadir)
        var etId = findViewById<EditText>(R.id.etId)
        var tvConsulta = findViewById<TextView>(R.id.tvConsulta)

        btir.setOnClickListener {
            val intent: Intent = Intent(this, table::class.java)
            startActivity(intent)
        }

        btnsubmit.setOnClickListener {
            if (etId.text.isNotBlank() &&
                etnameañadir.text.isNotBlank() &&
                etConsultaañadir.text.isNotBlank()) {

                val dato = hashMapOf(
                    "id" to etId.text.toString(),
                    "Nombre" to etnameañadir.text.toString(),
                    "Especialidad" to etConsultaañadir.text.toString())

                instanceFirestore.collection("prueba")
                    .document(etId.text.toString())
                    .set(dato)
                    .addOnSuccessListener { _ ->
                        tvConsulta.text ="añadido Correctamente"
                    }
                    .addOnFailureListener{
                        tvConsulta.text="no se pudo añadir"
                    }

            }


        }
    }
}