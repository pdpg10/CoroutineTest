package com.example.coroutinetest

import android.app.Application

class App : Application() {
    lateinit var api: ApiService

    override fun onCreate() {
        super.onCreate()
        api = ApiService.api
    }
}