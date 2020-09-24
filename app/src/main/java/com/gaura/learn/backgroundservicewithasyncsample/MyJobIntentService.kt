package com.gaura.learn.backgroundservicewithasyncsample

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import java.lang.Exception

class MyJobIntentService : JobIntentService() {

    companion object {
        val TAG = "MyJobIntentService"

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, 17, intent)
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate, ThreadName : " + Thread.currentThread().name)
        Toast.makeText(this, "Job Intent Service Started", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }


    override fun onHandleWork(intent: Intent) {

        Log.d(TAG, "onHandleWork, ThreadName : " + Thread.currentThread().name)

        val duration = intent.getIntExtra("intentParam", -1)
        var index = 1
        while (index <= duration) {
            Log.d(TAG, "Time passed: $index secs")
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {

            }
            index++
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy, ThreadName : " + Thread.currentThread().name)
        Toast.makeText(this, "Job Intent Service Finished", Toast.LENGTH_SHORT).show()
    }
}