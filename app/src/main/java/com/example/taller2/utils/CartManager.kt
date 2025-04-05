package com.example.taller2.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.taller2.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartManager {
    private const val PREFS_NAME = "cart_prefs"
    private const val CART_KEY = "cart_items"

    fun addProduct(context: Context, product: Product) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<MutableList<Product>>() {}.type

        val existingCartJson = prefs.getString(CART_KEY, null)
        val cart: MutableList<Product> = if (existingCartJson != null) {
            gson.fromJson(existingCartJson, type)
        } else {
            mutableListOf()
        }

        // Si ya existe el producto, aumenta cantidad
        val existingProduct = cart.find { it.name == product.name }
        if (existingProduct != null) {
            existingProduct.quantity += product.quantity
        } else {
            cart.add(product)
        }

        val updatedCartJson = gson.toJson(cart)
        prefs.edit().putString(CART_KEY, updatedCartJson).apply()
    }

    fun getCartItems(context: Context): MutableList<Product> {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<MutableList<Product>>() {}.type

        val existingCartJson = prefs.getString(CART_KEY, null)
        return if (existingCartJson != null) {
            gson.fromJson(existingCartJson, type)
        } else {
            mutableListOf()
        }
    }
}
