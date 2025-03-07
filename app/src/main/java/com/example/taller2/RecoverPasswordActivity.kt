package com.example.taller2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var textViewIniciaSesion: TextView
    private lateinit var editTextCorreo: EditText
    private lateinit var buttonRecuperar: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        textViewIniciaSesion = findViewById(R.id.tvTxtIniciaSesion)
        editTextCorreo = findViewById(R.id.etEmailRecuperacion)
        buttonRecuperar = findViewById(R.id.btEnviarSolicitud)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        textViewIniciaSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonRecuperar.setOnClickListener {
            validarCorreo()
        }
    }

    private fun validarCorreo() {
        val correoIngresado = editTextCorreo.text.toString().trim()
        val correoGuardado = sharedPreferences.getString("correo", "")

        if (correoIngresado == correoGuardado) {
            Toast.makeText(this, "Un correo ha sido enviado exitosamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Correo no encontrado", Toast.LENGTH_SHORT).show()
        }
    }
}
