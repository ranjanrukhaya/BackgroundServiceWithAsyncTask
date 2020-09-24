package com.gaura.learn.backgroundservicewithasyncsample

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.lang.ref.WeakReference

class MyBackgroundService : Service() {

    companion object {
        val TAG = "MyBackgroundService"
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate, ThreadName : " + Thread.currentThread().name)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand, ThreadName : " + Thread.currentThread().name)
        /*
        try {
            Thread.sleep(12000)
        } catch (e: Exception) {
        }*/

        MyAsyncTask(this).execute()

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind, ThreadName : " + Thread.currentThread().name)
        return null
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy, ThreadName : " + Thread.currentThread().name)
        super.onDestroy()
    }

    class MyAsyncTask(context: Context) : AsyncTask<Void, String, Void>() {

        var contextRef: WeakReference<Context> = WeakReference(context)

        override fun onPreExecute() {
            Log.d(TAG, "onPreExecute, ThreadName : " + Thread.currentThread().name)
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            Log.d(TAG, "doInBackground, ThreadName : " + Thread.currentThread().name)
            var index = 1
            while (index <= 12) {
                publishProgress("Time passed: $index secs")
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {

                }
                index++
            }

            return null
        }

        override fun onProgressUpdate(vararg values: String?) {
            Log.d(TAG, "onProgressUpdate, ThreadName : " + Thread.currentThread().name)
            super.onProgressUpdate(*values)
            Toast.makeText(contextRef.get(), values[0], Toast.LENGTH_SHORT).show()
        }

        override fun onPostExecute(result: Void?) {
            Log.d(TAG, "onPostExecute, ThreadName : " + Thread.currentThread().name)
            super.onPostExecute(result)
        }
    }
}