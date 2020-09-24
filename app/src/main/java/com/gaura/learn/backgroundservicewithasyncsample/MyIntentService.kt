package com.gaura.learn.backgroundservicewithasyncsample

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.Exception

class MyIntentService : IntentService("MyIntentThread") {

    companion object {
        val TAG = "MyIntentService"
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate, ThreadName : " + Thread.currentThread().name)
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent, ThreadName : " + Thread.currentThread().name)

        var index = 1
        while (index <= 12) {
            Log.d(TAG, "Time passed: $index secs")
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {

            }
            index++
        }

        val localIntent = Intent("my.own.broadcast")
        localIntent.putExtra("key", index)
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy, ThreadName : " + Thread.currentThread().name)
        super.onDestroy()
    }
}