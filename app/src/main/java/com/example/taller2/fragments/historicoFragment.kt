package com.example.taller2.fragments

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.taller2.R

class historicoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_historico, container, false)
        val layoutHistorico = view.findViewById<LinearLayout>(R.id.layoutHistorico)

        val sharedPreferences = requireContext().getSharedPreferences("HistoricoLectura", Context.MODE_PRIVATE)
        val historico = sharedPreferences.all.toSortedMap()

        if (historico.isEmpty()) {
            val noDataTextView = TextView(context).apply {
                text = "No hay resultados guardados aún."
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                textSize = 16f
                setPadding(20, 30, 20, 30)
                gravity = Gravity.CENTER
            }
            layoutHistorico.addView(noDataTextView)
        } else {
            for ((clave, valor) in historico.entries.reversed()) {

                // Creamos una tarjeta visual (LinearLayout)
                val card = LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(30, 30, 30, 30)
                    val shape = GradientDrawable()
                    shape.setColor(ContextCompat.getColor(requireContext(), R.color.white))
                    shape.cornerRadius = 20f
                    shape.setStroke(3, ContextCompat.getColor(requireContext(), R.color.darkblue))
                    background = shape
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, 0, 0, 30)
                    }
                }

                val fecha = TextView(context).apply {
                    text = "📅 $clave"
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.darkblue))
                    textSize = 18f
                }

                val contenido = TextView(context).apply {
                    text = valor.toString()
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    textSize = 16f
                    setPadding(0, 10, 0, 0)
                }

                card.addView(fecha)
                card.addView(contenido)
                layoutHistorico.addView(card)
            }
        }

        return view
    }
}
