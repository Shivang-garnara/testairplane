package com.qtonz.testairplane

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qtonz.testairplane.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startService(Intent(this,MyAirplaneModeService::class.java))
//        startForegroundService(Intent(this, MyAirplaneModeService::class.java))
        startForegroundService(Intent(this, ConnectivityModeService::class.java))
    }
}
