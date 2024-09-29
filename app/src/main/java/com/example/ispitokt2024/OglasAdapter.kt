package com.example.ispitokt2024

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ispitokt2024.models.Oglas
import org.w3c.dom.Text

class OglasAdapter(private var oglasi:List<Oglas>): RecyclerView.Adapter<OglasAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
         var naslov:TextView = itemView.findViewById<TextView>(R.id.naslov)
         var opis:TextView = itemView.findViewById<TextView>(R.id.opis)
         //var datum = itemView.findViewById<TextView>(R.id.datum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_oglas,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return oglasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var oglas = oglasi[position]

        holder.naslov.text = oglas.naslov
        holder.opis.text = oglas.opis

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetaljiActivity::class.java).apply {
                putExtra("detaljanOpis", oglas.detaljan_opis)
                putExtra("autor", oglas.autor)
                putExtra("datum", oglas.datum)

            }
            context.startActivity(intent)
        }


    }
}