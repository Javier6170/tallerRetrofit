package com.android.retrofit

import com.android.retrofit.data.datasource.APIService
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import strikt.api.*

class MarvelUnitTest {

    val api = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443/v1/public/characters/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(APIService::class.java)

    @Test
    fun obtenerPostResponseTest(){
        val call = api.getMarvel("1011334?ts=1&apikey=745bc715bae846b08d7fd44232433301&hash=683b0054c69c2aa30d8e6bee31d7a636")
        expectThat(call.execute()){
            assertThat("La respuesta es ok"){
                it.code() == 200
            }
            assertThat("El nombre del avenger es correcto"){
                it.body()!!.data.results.first().name == "3-D Man"
            }
        }

    }

}
