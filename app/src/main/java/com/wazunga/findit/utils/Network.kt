@file:Suppress("DEPRECATION")

package com.wazunga.nhulox97.findit.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService

/*
* Created by wazunga.nhulox97 on 18/11/19.
*/

class Network {
    companion object {
        fun networkExists(activity: AppCompatActivity): Boolean {
            val connectivityManager = activity
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null
        }
    }
}