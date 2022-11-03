package com.android.retrofit.data.datasource

import com.android.retrofit.data.datasource.dto.CharacterDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getMarvel(@Url url: String): Call<CharacterDTO>
}