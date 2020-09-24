package com.gaura.learn.backgroundservicewithasyncsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val intent = Intent(context, MyIntentService::class.java)
            intent.putExtra("intentParam", 12)
            context.startService(intent)
        }
    }
}