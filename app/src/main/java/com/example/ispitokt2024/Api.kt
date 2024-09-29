package com.example.ispitokt2024

import android.telecom.Call
import com.example.ispitokt2024.models.NoviOglas
import com.example.ispitokt2024.models.Oglas
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface Api {

    @GET("api/oglasi/")
    fun getAll():retrofit2.Call<List<Oglas>>

    @GET("api/oglasi/search")
    fun search(@Query("naslov") naslov:String):retrofit2.Call<List<Oglas>>

    @POST("api/oglasi/")
    fun add(@Body noviOglas:NoviOglas):retrofit2.Call<NoviOglas>

}