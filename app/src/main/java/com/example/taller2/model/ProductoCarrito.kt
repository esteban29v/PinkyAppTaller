package com.example.taller2.model

data class ProductoCarrito(
    val nombre: String,
    val cantidad: Int,
    val precio: Double
) {
    fun getSubtotal(): Double {
        return cantidad * precio
    }
}
