package com.sdk.broadcastcontentprovider.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.sdk.broadcastcontentprovider.ForegroundServiceActivity
import com.sdk.broadcastcontentprovider.R

class MyForegroundService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private val TAG = "MyForegroundService"
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.isLooping = true
        mediaPlayer?.setVolume(100f, 100f)
        createNotification()
        Log.d(TAG, "onCreate: created")
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "123",
                "Music Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, ForegroundServiceActivity::class.java)
        val pending = PendingIntent.getActivity(this, 100, notificationIntent, 0)
        val notification = Notification.Builder(this, "123")
            .setContentTitle("Music notification")
            .setContentText("Old Town Road")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pending)
            .build()
        mediaPlayer?.start()
        startForeground(101, notification)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        Log.d(TAG, "onDestroy: ")
    }
}