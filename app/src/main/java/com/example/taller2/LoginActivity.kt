package com.example.taller2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewRegistrate: TextView
    private lateinit var textViewRecuperarClave: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.etUsername)
        editTextPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.btIngresar)
        textViewRegistrate = findViewById(R.id.tvTxtRegistrate)
        textViewRecuperarClave = findViewById(R.id.tvRecuperarClave)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        buttonLogin.setOnClickListener {
            if (validarCredenciales()) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Redirigir a la pantalla de registro al hacer clic en "Regístrate"
        textViewRegistrate.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        textViewRecuperarClave.setOnClickListener {
            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarCredenciales(): Boolean {
        val emailIngresado = editTextEmail.text.toString().trim()
        val passwordIngresada = editTextPassword.text.toString().trim()

        val emailGuardado = sharedPreferences.getString("correo", "")
        val passwordGuardada = sharedPreferences.getString("clave", "")

        Log.d("LOGIN ACTIVITY correo guardado:", emailGuardado.toString())
        Log.d("LOGIN ACTIVITY clave guardado:", passwordGuardada.toString())

        return emailIngresado == emailGuardado && passwordIngresada == passwordGuardada
    }
}
