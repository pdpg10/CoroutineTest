package com.example.coroutinetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

//https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#2


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            loadIp()
        }
    }

    private fun loadIp() {
        GlobalScope.launch(Dispatchers.Main) {
            val api = (application as App).api
            val ipModel = withContext(Dispatchers.IO) { api.loadMyIp() }
            tv.text = ipModel.ip
        }
    }

    private fun startDefaultTimer() {
        thread {
            (0..100).forEach {
                Thread.sleep(1000)
                tv.text = "$it"
            }
        }
    }

    //room 2.2.1 alpha suspend
    //retrofit 2.6.0 suspend
    private fun startCoroutineTimer() {
        GlobalScope.launch(Dispatchers.Main) {
            (0..100).forEach {
                delay(1000)
                tv.text = "$it"
            }
        }
    }
}
