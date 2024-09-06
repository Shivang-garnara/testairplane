package com.qtonz.testairplane

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class BootReceiver : BroadcastReceiver() {

    //    override fun onReceive(context: Context?, intent: Intent?) {
//        Log.d("BootReceiver", "onReceive")
//        if (intent != null && intent.action != null) {
////            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show()
//            Log.d("BootReceiver", "IN-side First if")
//            if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//                println("helllllloo")
//            }
//        }
//    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootReceiver", "onReceive")
            println("helllllloo")
            Toast.makeText(context!!, "Device Booted", Toast.LENGTH_LONG).show()
        }
    }
}
