package com.example.taller2.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.taller2.activities.CalculatorActivity
import com.example.taller2.R

class PerfilFragment : Fragment() {

    private lateinit var editTextNombres: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonActualizar: Button
    private lateinit var buttonCalculator: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        // Inicializar vistas
        editTextNombres = view.findViewById(R.id.etTitulo)
        editTextApellidos = view.findViewById(R.id.etPaginas)
        editTextCorreo = view.findViewById(R.id.etCorreo)
        editTextTelefono = view.findViewById(R.id.etTelefono)
        buttonActualizar = view.findViewById(R.id.btActualizar)
        buttonCalculator = view.findViewById(R.id.btCalculator)

        // Inicializar SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)

        cargarDatos()

        buttonActualizar.setOnClickListener {
            guardarDatos()
        }

        buttonCalculator.setOnClickListener {
            startActivity(Intent(requireContext(), CalculatorActivity::class.java))
        }

        return view
    }

    private fun cargarDatos() {
        editTextNombres.setText(sharedPreferences.getString("nombres", ""))
        editTextApellidos.setText(sharedPreferences.getString("apellidos", ""))
        editTextCorreo.setText(sharedPreferences.getString("correo", ""))
        editTextTelefono.setText(sharedPreferences.getString("telefono", ""))
    }

    private fun guardarDatos() {
        with(sharedPreferences.edit()) {
            putString("nombres", editTextNombres.text.toString().trim())
            putString("apellidos", editTextApellidos.text.toString().trim())
            putString("correo", editTextCorreo.text.toString().trim())
            putString("telefono", editTextTelefono.text.toString().trim())
            apply()
        }
        Toast.makeText(requireContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }
}
