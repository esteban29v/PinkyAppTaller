package com.example.taller2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity: AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var etPaginas: EditText
    private lateinit var etTiempo: EditText
    private lateinit var etFecha: EditText

    private lateinit var rsVelocidad: TextView
    private lateinit var rsPaginas: TextView
    private lateinit var rsTiempo: TextView

    private lateinit var buttonRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        Log.d("CalculadoraActivity", "onCreate: Inicializando el activity de calculadora")

        etTitulo = findViewById(R.id.etTitulo)
        etPaginas = findViewById(R.id.etPaginas)
        etTiempo = findViewById(R.id.etTiempo)
        etFecha = findViewById(R.id.etFecha)

        buttonRegistro = findViewById(R.id.btRegistrarM)

        buttonRegistro.setOnClickListener {
            registrarMetricas()
        }

    }

    fun registrarMetricas() {
        val titulo = etTitulo.text.toString().trim()
        val paginas = etPaginas.text.toString().toDouble()
        val tiempo = etTiempo.text.toString().toDouble()
        val fecha = etFecha.text.toString().trim()

        val velocidad = paginas / tiempo

        rsVelocidad = findViewById(R.id.rsVelocidad)
        rsVelocidad.text = velocidad.toString()
        Toast.makeText(this, "Métrica guardada exitosamente!", Toast.LENGTH_SHORT).show()
    }
}