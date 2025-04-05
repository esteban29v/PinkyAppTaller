package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taller2.R
import com.example.taller2.model.Product
import com.example.taller2.utils.CartManager

class BeerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beer, container, false)

        val btnAddProduct1 = view.findViewById<Button>(R.id.btn_vodka_1)
        btnAddProduct1.setOnClickListener {
            val product1 = Product("Cerveza Andina", 1, 250.00)
            CartManager.addProduct(requireContext(), product1)
            Toast.makeText(requireContext(), "Cerveza Andina vidrio añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct2 = view.findViewById<Button>(R.id.btn_vodka_2)
        btnAddProduct2.setOnClickListener {
            val product2 = Product("Cerveza Central", 1, 120.00)
            CartManager.addProduct(requireContext(), product2)
            Toast.makeText(requireContext(), "Cerveza central añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct3 = view.findViewById<Button>(R.id.btn_vodka_3)
        btnAddProduct3.setOnClickListener {
            val product3 = Product("Cerveza andina lata", 1, 350.00)
            CartManager.addProduct(requireContext(), product3)
            Toast.makeText(requireContext(), "Cerveza andina lata añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct4 = view.findViewById<Button>(R.id.btn_vodka_4)
        btnAddProduct4.setOnClickListener {
            val product4 = Product("Cerveza Budweiser", 1, 450.00)
            CartManager.addProduct(requireContext(), product4)
            Toast.makeText(requireContext(), "Cerveza Budweiser añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct5 = view.findViewById<Button>(R.id.btn_vodka_5)
        btnAddProduct5.setOnClickListener {
            val product5 = Product("Cerveza Heineken", 1, 750.00)
            CartManager.addProduct(requireContext(), product5)
            Toast.makeText(requireContext(), "Cerveza Heineken añadido al carrito", Toast.LENGTH_SHORT).show()
        }


        return view
    }
}
