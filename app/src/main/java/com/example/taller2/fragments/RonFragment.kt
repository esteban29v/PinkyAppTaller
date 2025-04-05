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

class RonFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ron, container, false)

        val btnAddProduct1 = view.findViewById<Button>(R.id.btn_ron_1)
        btnAddProduct1.setOnClickListener {
            val product1 = Product("Ron añejo 12 años", 1, 250.00)
            CartManager.addProduct(requireContext(), product1)
            Toast.makeText(requireContext(), "Ron añejo 12 años añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct2 = view.findViewById<Button>(R.id.btn_ron_2)
        btnAddProduct2.setOnClickListener {
            val product2 = Product("Ron Añejo Pajarito", 1, 120.00)
            CartManager.addProduct(requireContext(), product2)
            Toast.makeText(requireContext(), "Ron Añejo Pajarito añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct3 = view.findViewById<Button>(R.id.btn_ron_3)
        btnAddProduct3.setOnClickListener {
            val product3 = Product("Ron Caldas", 1, 350.00)
            CartManager.addProduct(requireContext(), product3)
            Toast.makeText(requireContext(), "Ron Caldas añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct4 = view.findViewById<Button>(R.id.btn_ron_4)
        btnAddProduct4.setOnClickListener {
            val product4 = Product("Ron Barcelo", 1, 350.00)
            CartManager.addProduct(requireContext(), product4)
            Toast.makeText(requireContext(), "Ron Barcelo añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct5 = view.findViewById<Button>(R.id.btn_ron_5)
        btnAddProduct5.setOnClickListener {
            val product5 = Product("Ron Classico Premium", 1, 350.00)
            CartManager.addProduct(requireContext(), product5)
            Toast.makeText(requireContext(), "Ron Classico Premium añadido al carrito", Toast.LENGTH_SHORT).show()
        }


        return view
    }
}
