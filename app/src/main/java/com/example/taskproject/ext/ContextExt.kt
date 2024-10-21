package com.example.taskproject.ext

import android.content.Context
import android.net.ConnectivityManager

fun Context.getStringByName(aString: String): String? {
    return try {
        val resId: Int = resources.getIdentifier(aString, "string", packageName)
        getString(resId)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Context.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    val cellularNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    return wifiNetwork?.isConnected == true || cellularNetwork?.isConnected == true
}