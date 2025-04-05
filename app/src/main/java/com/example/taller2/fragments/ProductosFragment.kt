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

class ProductosFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val btnAddProduct1 = view.findViewById<Button>(R.id.btn_add_product1)
        btnAddProduct1.setOnClickListener {
            val product1 = Product("Producto", 1, 250.00)
            CartManager.addProduct(requireContext(), product1)
            Toast.makeText(requireContext(), "Producto1 añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        val btnAddProduct2 = view.findViewById<Button>(R.id.btn_add_product2)
        btnAddProduct2.setOnClickListener {
            val product2 = Product("Producto 2", 1, 500.00)
            CartManager.addProduct(requireContext(), product2)
            Toast.makeText(requireContext(), "Producto2añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
