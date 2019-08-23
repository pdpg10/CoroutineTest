package com.example.coroutinetest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("?format=json")
    suspend fun loadMyIp(): IpModel

    companion object {
        val api: ApiService
            get() {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.ipify.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit.create(ApiService::class.java)
            }

    }
}