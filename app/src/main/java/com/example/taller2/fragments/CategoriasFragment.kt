package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.taller2.R

class CategoriasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)

        // Referencias a los botones
        val btnWhiskies = view.findViewById<Button>(R.id.btnWhiskies)
        val btnRon = view.findViewById<Button>(R.id.btnRon)
        val btnVodka = view.findViewById<Button>(R.id.btnVodka)
        val btnBeer = view.findViewById<Button>(R.id.btnBeer)
        val btnTequila = view.findViewById<Button>(R.id.btnTequila)


        return view
    }
}
