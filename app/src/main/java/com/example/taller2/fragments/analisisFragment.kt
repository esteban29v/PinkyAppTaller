package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.taller2.R
import java.text.SimpleDateFormat
import java.util.*

class analisisFragment : Fragment() {

    private lateinit var etTitulo: EditText
    private lateinit var etPaginas: EditText
    private lateinit var etTiempoDiario: EditText
    private lateinit var etFechaObjetivo: EditText
    private lateinit var tvResultados: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnGuardar: Button
    private var resultadoFinal: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_analisis, container, false)

        etTitulo = view.findViewById(R.id.etTitulo)
        etPaginas = view.findViewById(R.id.etPaginas)
        etTiempoDiario = view.findViewById(R.id.etTiempoDiario)
        etFechaObjetivo = view.findViewById(R.id.etFechaObjetivo)
        tvResultados = view.findViewById(R.id.tvResultados)
        btnCalcular = view.findViewById(R.id.btnCalcular)
        btnGuardar = view.findViewById(R.id.btnGuardar)

        btnCalcular.setOnClickListener {
            calcularPlanLectura()
        }

        btnGuardar.setOnClickListener {
            guardarResultados()
        }

        return view
    }

    private fun calcularPlanLectura() {
        try {
            val paginas = etPaginas.text.toString().toInt()
            val tiempoDiario = etTiempoDiario.text.toString().toInt()
            val fechaObjetivo = SimpleDateFormat("yyyy-MM-dd").parse(etFechaObjetivo.text.toString())!!
            val diasRestantes = ((fechaObjetivo.time - Date().time) / (1000 * 60 * 60 * 24)).toInt()

            if (diasRestantes <= 0) {
                Toast.makeText(context, "La fecha objetivo debe ser posterior a hoy", Toast.LENGTH_SHORT).show()
                return
            }
            if (tiempoDiario <= 0) {
                Toast.makeText(context, "El tiempo diario debe ser mayor a cero", Toast.LENGTH_SHORT).show()
                return
            }

            val paginasDiarias = paginas.toFloat() / diasRestantes
            val velocidadLectura = paginasDiarias / (tiempoDiario.toFloat() / 60f) // páginas por hora
            val tiempoEstimadoTotalHoras = paginas / velocidadLectura

            resultadoFinal = """
            📘 Plan de lectura para: ${etTitulo.text}
            - Días disponibles: $diasRestantes
            - Páginas por día: ${"%.2f".format(paginasDiarias)}
            - Velocidad estimada: ${"%.2f".format(velocidadLectura)} páginas/hora
            - Tiempo estimado total: ${"%.2f".format(tiempoEstimadoTotalHoras)} horas
        """.trimIndent()

            tvResultados.text = resultadoFinal

        } catch (e: Exception) {
            Toast.makeText(context, "Verifica los datos ingresados", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarResultados() {
        if (resultadoFinal.isBlank()) {
            Toast.makeText(context, "Primero debes calcular un plan de lectura", Toast.LENGTH_SHORT).show()
            return
        }

        val sharedPreferences = requireContext().getSharedPreferences("HistoricoLectura", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val titulo = etTitulo.text.toString()
        val clave = "$fecha - $titulo"

        editor.putString(clave, resultadoFinal)
        editor.apply()

        Toast.makeText(context, "Resultado guardado", Toast.LENGTH_SHORT).show()
    }
}
