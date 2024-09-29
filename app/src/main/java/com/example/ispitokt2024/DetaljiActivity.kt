package com.example.ispitokt2024

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetaljiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalji)

        var detaljanOpis = intent.getStringExtra("detaljanOpis")
        var autor = intent.getStringExtra("autor")
        var datum = intent.getStringExtra("datum")

        findViewById<TextView>(R.id.tvDetaljanOpis).text = detaljanOpis
        findViewById<TextView>(R.id.tvAutor).text = autor
        findViewById<TextView>(R.id.tvDatum).text = datum

    }
}