package com.qtonz.testairplane

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ConnectivityModeService : Service() {

    private val connectivityReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                val isConnected = activeNetwork?.isConnectedOrConnecting == true

                if (isConnected) {
                    Toast.makeText(this@ConnectivityModeService,"Connected to the internet",Toast.LENGTH_LONG).show()
                    Log.d("ConnectivityService", "Connected to the internet")
                } else {
                    Toast.makeText(this@ConnectivityModeService,"Disconnected to the internet",Toast.LENGTH_LONG).show()
                    Log.d("ConnectivityService", "Disconnected from the internet")
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, filter)
        startForegroundService()
    }

    private fun startForegroundService() {
        val notification = createNotification()
        startForeground(1, notification)
    }

    private fun createNotification(): Notification {
        val notificationChannelId = "CONNECTIVITY_CHANGE_SERVICE_CHANNEL"

        val channel = NotificationChannel(
            notificationChannelId,
            "Connectivity Change Service",
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)

        return NotificationCompat.Builder(this, notificationChannelId)
            .setContentTitle("Connectivity Monitoring")
            .setContentText("Service is monitoring connectivity changes.")
            .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your app's icon
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
