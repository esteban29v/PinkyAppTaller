package com.example.taller2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var editTextNombres: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonActualizar: Button
    private lateinit var buttonCalculator: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inicializar vistas
        editTextNombres = findViewById(R.id.etTitulo)
        editTextApellidos = findViewById(R.id.etPaginas)
        editTextCorreo = findViewById(R.id.etCorreo)
        editTextTelefono = findViewById(R.id.etTelefono)
        buttonActualizar = findViewById(R.id.btActualizar)
        buttonCalculator = findViewById(R.id.btCalculator)

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        // Cargar los datos guardados en los EditText
        cargarDatos()

        // Guardar cambios cuando se presione el botón
        buttonActualizar.setOnClickListener {
            guardarDatos()
        }

        buttonCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun cargarDatos() {
        // Obtener los valores almacenados en SharedPreferences
        val nombres = sharedPreferences.getString("nombres", "")
        val apellidos = sharedPreferences.getString("apellidos", "")
        val correo = sharedPreferences.getString("correo", "")
        val telefono = sharedPreferences.getString("telefono", "")

        // Asignar los valores a los EditText
        editTextNombres.setText(nombres)
        editTextApellidos.setText(apellidos)
        editTextCorreo.setText(correo)
        editTextTelefono.setText(telefono)
    }

    private fun guardarDatos() {
        val editor = sharedPreferences.edit()

        // Guardar los valores actuales de los EditText en SharedPreferences
        editor.putString("nombres", editTextNombres.text.toString().trim())
        editor.putString("apellidos", editTextApellidos.text.toString().trim())
        editor.putString("correo", editTextCorreo.text.toString().trim())
        editor.putString("telefono", editTextTelefono.text.toString().trim())

        // Aplicar los cambios
        editor.apply()

        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }
}
