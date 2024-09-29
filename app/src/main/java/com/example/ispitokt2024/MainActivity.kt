package com.example.ispitokt2024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ispitokt2024.models.Oglas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var BASE_URL = "http://10.0.2.2:5000/"
    private lateinit var api:Api;
    private  lateinit var adapter: OglasAdapter

    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         recyclerView = findViewById<RecyclerView>(R.id.rvOglasi)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var btnAdd = findViewById<Button>(R.id.addBtn)
        btnAdd.setOnClickListener {
            var context = this@MainActivity
            val intent = Intent(context,UnosActivity::class.java)
            context.startActivity(intent)
        }

        var seacrhView = findViewById<SearchView>(R.id.searchView)
        seacrhView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchOglasi(it)
                }
                return true
            }

        })

         api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)


        getOglasi()


    }
    private fun getOglasi() {



        api.getAll().enqueue(object : Callback<List<Oglas>>{
            override fun onResponse(call: Call<List<Oglas>>, response: Response<List<Oglas>>) {
                if(response.isSuccessful){
                    Log.i("TAG","response body: ${response.body()}")
                    response.body()?.let { oglasi ->
                        Log.i("TAG",oglasi.toString())
                        adapter = OglasAdapter(oglasi)
                        recyclerView.adapter = adapter
                     }
                }
            }

            override fun onFailure(call: Call<List<Oglas>>, t: Throwable) {
                Log.i("TAG","Error: ${t.message}")
            }

        })
    }
    private fun searchOglasi(oglas:String) {



        api.search(oglas).enqueue(object : Callback<List<Oglas>> {
            override fun onResponse(
                call: Call<List<Oglas>>,
                response: Response<List<Oglas>>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "Pretraga uspešna")
                    response.body()?.let { oglasi ->
                        adapter = OglasAdapter(oglasi)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Oglas>>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }
}