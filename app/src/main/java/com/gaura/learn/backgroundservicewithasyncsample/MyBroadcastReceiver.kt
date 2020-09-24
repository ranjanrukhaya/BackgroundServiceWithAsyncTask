package com.gaura.learn.backgroundservicewithasyncsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {

    companion object {
        val TAG = "MyBroadcastReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive, ThreadName : " + Thread.currentThread().name)

        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            //val i = Intent(context, MyIntentService::class.java)
            //intent.putExtra("intentParam", 12)
            //context.startService(i)

            val i = Intent(context, MyJobIntentService::class.java)
            i.putExtra("intentParam", 12)
            MyJobIntentService.enqueueWork(context, i)
        }
    }
}