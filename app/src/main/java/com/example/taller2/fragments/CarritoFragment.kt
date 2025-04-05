package com.example.taller2.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.R
import com.example.taller2.adapters.CarritoAdapter
import com.example.taller2.model.ProductoCarrito
import com.example.taller2.utils.CartManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarritoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotalProductos: TextView
    private lateinit var tvPrecioTotal: TextView
    private lateinit var btnPagar: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: CarritoAdapter
    private var listaProductos = mutableListOf<ProductoCarrito>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)

        recyclerView = view.findViewById(R.id.recycler_carrito)
        tvTotalProductos = view.findViewById(R.id.tv_total_productos)
        tvPrecioTotal = view.findViewById(R.id.tv_precio_total)
        btnPagar = view.findViewById(R.id.btn_pagar)

        sharedPreferences = requireContext().getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CarritoAdapter(requireContext(), listaProductos) { actualizarTotales() }
        recyclerView.adapter = adapter

        cargarCarrito()

        btnPagar.setOnClickListener {
        }

        return view
    }

    private fun cargarCarrito() {
        val productos = CartManager.getCartItems(requireContext())
        listaProductos.clear()
        listaProductos.addAll(productos.map {
            ProductoCarrito(it.name, it.quantity, it.price)
        })
        adapter.notifyDataSetChanged()
        actualizarTotales()
    }

    private fun actualizarTotales() {
        val totalProductos = listaProductos.sumOf { it.cantidad }
        val precioTotal = listaProductos.sumOf { it.getSubtotal() }

        tvTotalProductos.text = "Total de productos: $totalProductos"
        tvPrecioTotal.text = "Precio total: $$precioTotal"
    }
}
