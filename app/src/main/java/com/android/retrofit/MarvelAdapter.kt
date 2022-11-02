package com.android.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.retrofit.data.datasource.dto.ItemLista

class MarvelAdapter(var lista:ArrayList<ItemLista>) : RecyclerView.Adapter<MarvelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(layoutInflater.inflate(R.layout.item_marvel, parent, false))
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size
}