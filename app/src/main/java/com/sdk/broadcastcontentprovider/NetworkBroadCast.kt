package com.sdk.broadcastcontentprovider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NetworkBroadCast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(!hasInternet(context!!)) {
            Toast.makeText(context, "No internet available", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, NoIntentActivity::class.java))
        }
    }
    private fun hasInternet(context: Context): Boolean {
        val networkUtil = NetworkUtil(context)
        return networkUtil.isConnected()
    }
}