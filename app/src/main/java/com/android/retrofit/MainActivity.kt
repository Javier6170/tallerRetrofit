package com.android.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.retrofit.data.datasource.APIService
import com.android.retrofit.data.datasource.dto.CharacterDTO
import com.android.retrofit.data.datasource.dto.ItemLista
import com.android.retrofit.data.datasource.dto.Result
import com.android.retrofit.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MarvelAdapter
    private var marvelImages:String = ""
    private var nombre: String = ""
    private var descripcion: String = ""
    private lateinit var lista:ArrayList<ItemLista>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        lista = ArrayList()

        val retrofit = getRetrofit()
        val servicio = retrofit.create(APIService::class.java)

        initRecyclerView()
        mostrarMarvel(servicio,"1009144")

    }

    private fun initRecyclerView() {
        adapter = MarvelAdapter(lista)
        binding.rcMarvel.layoutManager = LinearLayoutManager(this)
        binding.rcMarvel.adapter = adapter

    }

    private fun getRetrofit(): Retrofit{
         return Retrofit.Builder()
             .baseUrl("https://gateway.marvel.com:443/v1/public/characters/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
    }

    private fun mostrarMarvel(servicio: APIService, query: String){

            servicio.getMarvel("${query}?ts=1&apikey=745bc715bae846b08d7fd44232433301&hash=683b0054c69c2aa30d8e6bee31d7a636").enqueue( object : Callback<CharacterDTO>{
                override fun onResponse(
                    call: Call<CharacterDTO>,
                    response: Response<CharacterDTO>
                ) {

                    if (response.body()!=null){
                        val objeto = response.body()!!
                        val img = objeto.data.results.first().thumbnail
                        val rutaImg = "${img.path}.${img.extension}"
                        val nombre = objeto.data.results.first().name
                        val descripcion = objeto.data.results.first().description

                        val itemLista = ItemLista(rutaImg, nombre, descripcion)
                        lista.add(itemLista)
                        adapter.notifyItemInserted( lista.size-1 )

                    }else{
                        showError()
                    }

                }

                override fun onFailure(call: Call<CharacterDTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })


    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

}