package com.example.ispitokt2024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ispitokt2024.models.NoviOglas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class UnosActivity : AppCompatActivity() {

    lateinit var api:Api
    private var BASE_URL = "http://10.0.2.2:5000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_unos)

        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)


        var naslov = findViewById<EditText>(R.id.etNaslov)
        var opis = findViewById<EditText>(R.id.etOpis)
        var detaljanOpis = findViewById<EditText>(R.id.etDetaljanOpis)
        var autor = findViewById<EditText>(R.id.etAutor)
        var datum = findViewById<EditText>(R.id.etDatum)

        var btnAdd = findViewById<Button>(R.id.buttonAdd)
        btnAdd.setOnClickListener {

            var naslov2 = naslov.text.toString()
            var opis2 = opis.text.toString()
            var detaljanOpis2 = detaljanOpis.text.toString()
            var autor2 = autor.text.toString()
            var datum2 = datum.text.toString()

            var noviOglas = NoviOglas(naslov2,opis2,detaljanOpis2,autor2,datum2)
            dodajOglas(noviOglas)
        }
    }

    fun dodajOglas(novi:NoviOglas) {
        api.add(novi).enqueue(object : Callback<NoviOglas> {
            override fun onResponse(call: Call<NoviOglas>, response: Response<NoviOglas>) {
                if(response.isSuccessful){
                    var oglas = response.body()
                    Log.i("TAG","Dodat novi oglas: ${oglas}")
                    val intent = Intent(this@UnosActivity,MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Log.e("error","Nesuspesno dodavanje!")
                }
            }

            override fun onFailure(call: Call<NoviOglas>, t: Throwable) {
                Log.e("TAG","Error: ${t.message}")
            }

        })
    }
}