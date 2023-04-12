package com.sdk.broadcastcontentprovider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sdk.broadcastcontentprovider.service.MyForegroundService

class ForegroundServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)

        findViewById<Button>(R.id.button4).setOnClickListener {
            startService(Intent(this,MyForegroundService::class.java))
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            stopService(Intent(this,MyForegroundService::class.java))
        }
    }
}