package com.gaura.learn.backgroundservicewithasyncsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

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

    fun onStartIntentService(view: View) {
        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra("intentParam", 12)
        startService(intent)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("my.own.broadcast")
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(localBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastReceiver)
    }

    private val localBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val result = intent.getIntExtra("key", -1)
            result_text.text = "Intent Service ran for $result secs"
        }
    }
}