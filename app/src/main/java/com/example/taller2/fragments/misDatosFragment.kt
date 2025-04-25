package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.taller2.R

class misDatosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_misdatos, container, false)

        // Obtener referencias a los TextViews
        val txtNombre = view.findViewById<TextView>(R.id.txtNombre)
        val txtEdad = view.findViewById<TextView>(R.id.txtEdad)
        val txtCorreo = view.findViewById<TextView>(R.id.txtCorreo)
        val txtPrograma = view.findViewById<TextView>(R.id.txtPrograma)
        val txtSemestre = view.findViewById<TextView>(R.id.txtSemestre)

        // Acceder a SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("MisDatos", Context.MODE_PRIVATE)

        // Cargar los datos
        txtNombre.text = "Nombre: " + sharedPref.getString("nombre", "No disponible")
        txtEdad.text = "Edad: " + sharedPref.getInt("edad", 0).toString()
        txtCorreo.text = "Correo: " + sharedPref.getString("correo", "No disponible")
        txtPrograma.text = "Programa: " + sharedPref.getString("programa", "No disponible")
        txtSemestre.text = "Semestre: " + sharedPref.getInt("semestre", 0).toString()

        return view
    }
}
