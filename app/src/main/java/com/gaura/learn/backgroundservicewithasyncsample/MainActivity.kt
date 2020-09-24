package com.gaura.learn.backgroundservicewithasyncsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartService(view: View) {
        startService(Intent(this, MyBackgroundService::class.java))
    }

    fun onStopService(view: View) {
        stopService(Intent(this, MyBackgroundService::class.java))
    }
}