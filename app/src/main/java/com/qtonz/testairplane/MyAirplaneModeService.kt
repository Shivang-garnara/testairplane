package com.qtonz.testairplane

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MyAirplaneModeService : Service() {

    private val airplaneModeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                if (isAirplaneModeOn) {
                    Toast.makeText(
                        this@MyAirplaneModeService,
                        "Airplane mode is ON",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("MyAirplaneModeService", "Airplane mode is ON")
                } else {
                    Toast.makeText(
                        this@MyAirplaneModeService,
                        "Airplane mode is OFF",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("MyAirplaneModeService", "Airplane mode is OFF")
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeReceiver, filter)
//        startForegroundService()
    }

    private fun startForegroundService() {
//        val notification = createNotification()
//        startForeground(1, notification)
    }

    private fun createNotification(): Notification {
        val notificationChannelId = "MY_AIRPLANE_MODE_SERVICE_CHANNEL"

        val channel = NotificationChannel(
            notificationChannelId,
            "Airplane Mode Service",
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)

        return NotificationCompat.Builder(this, notificationChannelId)
            .setContentTitle("Airplane Mode Monitoring")
            .setContentText("Service is monitoring airplane mode changes.")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneModeReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
