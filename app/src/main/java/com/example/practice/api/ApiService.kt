package com.example.practice.api

import com.example.practice.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<User>

    @GET("users")
    fun getData(): Call<MutableList<User>>
}