package com.grevi.msx.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.grevi.msx.utils.NoInternetException
import com.grevi.msx.utils.ResultResponse
import com.grevi.msx.utils.Status
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(context: Context) : Interceptor {

    val appContext = context.applicationContext


    private fun isAvailable() : Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val netInfo = cm.activeNetwork ?: return false
            val activeNetwork = cm.getNetworkCapabilities(netInfo) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val netInfo = cm.activeNetworkInfo ?: return false
            return netInfo.isConnected
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isAvailable()) {
            throw NoInternetException("Internet Unavailable")
        }
        return chain.proceed(chain.request())
    }
}