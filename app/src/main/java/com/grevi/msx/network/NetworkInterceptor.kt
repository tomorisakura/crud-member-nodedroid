package com.grevi.msx.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.grevi.msx.utils.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(context: Context) : Interceptor {

    val appContext = context.applicationContext

    @Suppress("DEPRECATION")
    private fun isAvailable() : Boolean {
        var result = false
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val netInfo = connectivityManager.activeNetwork
            val connection = connectivityManager.getNetworkCapabilities(netInfo)
             result = connection != null && (connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                     connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                result = (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
            }
        }

        return result
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isAvailable()) {
            throw NoConnectionException()
        }else {
            chain.proceed(chain.request())
        }
    }
}