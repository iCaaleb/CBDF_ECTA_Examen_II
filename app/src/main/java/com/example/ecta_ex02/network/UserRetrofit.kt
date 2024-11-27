package com.example.ecta_ex02.network

import com.example.ecta_ex02.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRetrofit {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val getInstanciaRetrofit: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }
}