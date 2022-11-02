package com.android.retrofit

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.retrofit.data.datasource.dto.ItemLista
import com.android.retrofit.databinding.ItemMarvelBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMarvelBinding.bind(view)

    fun bind(itemLista: ItemLista) {
        Log.e("IMAGEN", itemLista.imagen)
        Glide.with( binding.ivMarv.context )
            .load(itemLista.imagen)
            .into(binding.ivMarv)
        //el glide ya tengo la dependencia
        //Picasso.get().load(itemLista.imagen).into(binding.ivMarv)

        binding.nombreAvenger.text = itemLista.nombre
        binding.descripcionAvenger.text = itemLista.descripcion
    }
}