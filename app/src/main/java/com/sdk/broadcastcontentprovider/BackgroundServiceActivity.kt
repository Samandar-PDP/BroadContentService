package com.sdk.broadcastcontentprovider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sdk.broadcastcontentprovider.service.MyBackgroundService

class BackgroundServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_service)
        val stop: Button = findViewById(R.id.button2)
        val start: Button = findViewById(R.id.button3)

        stop.setOnClickListener {
            stopService(Intent(this, MyBackgroundService::class.java))
        }

        start.setOnClickListener {
            val intent = Intent(this, MyBackgroundService::class.java)
            startService(intent)
        }
    }
}