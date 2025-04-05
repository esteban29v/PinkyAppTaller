package com.example.taller2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextNombres: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var editTextRepetirContrasena: EditText
    private lateinit var checkBoxTerminos: CheckBox
    private lateinit var buttonRegistro: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d("RegistroActivity", "onCreate: Inicializando el activity de registro")

        editTextNombres = findViewById(R.id.etNombres)
        editTextApellidos = findViewById(R.id.etApellidos)
        editTextCorreo = findViewById(R.id.etEmail)
        editTextTelefono = findViewById(R.id.etTelefono)
        editTextContrasena = findViewById(R.id.etPassword)
        editTextRepetirContrasena = findViewById(R.id.etRepetirPassword)
        checkBoxTerminos = findViewById(R.id.cbTerminos)
        buttonRegistro = findViewById(R.id.btRegistrar)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        buttonRegistro.setOnClickListener {
            if (validarCampos()) {
                guardarDatos()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validarCampos(): Boolean {
        val nombres = editTextNombres.text.toString().trim()
        val apellidos = editTextApellidos.text.toString().trim()
        val correo = editTextCorreo.text.toString().trim()
        val telefono = editTextTelefono.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()
        val repetirContrasena = editTextRepetirContrasena.text.toString().trim()

        if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contrasena.isEmpty() || repetirContrasena.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (telefono.length < 10) {
            Toast.makeText(this, "Número de teléfono no válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (contrasena.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (contrasena != repetirContrasena) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!checkBoxTerminos.isChecked) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun guardarDatos() {
        val editor = sharedPreferences.edit()
        editor.putString("nombres", editTextNombres.text.toString().trim())
        editor.putString("apellidos", editTextApellidos.text.toString().trim())
        editor.putString("correo", editTextCorreo.text.toString().trim())
        editor.putString("telefono", editTextTelefono.text.toString().trim())
        editor.putString("clave", editTextContrasena.text.toString().trim())
        editor.apply()

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }
}
