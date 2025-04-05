package com.example.taller2.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.R
import com.example.taller2.model.ProductoCarrito
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarritoAdapter(
    private val context: Context,
    private var productos: MutableList<ProductoCarrito>,
    private val onUpdateTotal: () -> Unit
) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tv_nombre)
        val tvCantidad: TextView = view.findViewById(R.id.tv_cantidad)
        val tvPrecio: TextView = view.findViewById(R.id.tv_precio)
        val tvSubtotal: TextView = view.findViewById(R.id.tv_subtotal)
        val btnEliminar: Button = view.findViewById(R.id.btn_eliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]

        holder.tvNombre.text = producto.nombre
        holder.tvCantidad.text = "Cantidad: ${producto.cantidad}"
        holder.tvPrecio.text = "Precio: $${producto.precio}"
        holder.tvSubtotal.text = "Subtotal: $${producto.getSubtotal()}"

        holder.btnEliminar.setOnClickListener {
            productos.removeAt(position)
            notifyDataSetChanged()
            guardarCarrito()
            onUpdateTotal()
        }
    }

    override fun getItemCount(): Int = productos.size

    private fun guardarCarrito() {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(productos)
        editor.putString("carrito", json)
        editor.apply()
    }
}
