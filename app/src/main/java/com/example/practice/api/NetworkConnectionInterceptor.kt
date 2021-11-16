package com.example.practice.api
import com.example.practice.utility.NetworkManager
import com.example.practice.utility.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor(private val networkManager: NetworkManager) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkManager.isNetworkAvailable) {
            throw NoInternetException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())

    }
}