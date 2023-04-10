package com.sdk.broadcastcontentprovider.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyBackgroundService: Service() {
    private var job: Job? = null
    private var counter = 0
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job = MainScope().launch {
            while (counter <= 100) {
                counter++
                Log.d("@@@", "onStartCommand: $counter")
                delay(1000L)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}