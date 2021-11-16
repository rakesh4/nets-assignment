package com.example.practice.di


import com.example.practice.BuildConfig
import com.example.practice.api.ApiService
import com.example.practice.api.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val httpModule = module {
    val tag = "Retrofit: "

    fun initRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .build()
    }

    fun initHttpClient(
        netConInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(netConInterceptor)
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    fun initRetrofitHttpService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { NetworkConnectionInterceptor(get()) }
    single { initHttpClient(get()) }
    single { initRetrofit(get()) }
    single { initRetrofitHttpService(get()) }
}
