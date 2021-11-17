package com.example.practice.api

import com.example.practice.models.User
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET(HttpConstants.METHOD_GET_USER_LIST)
    suspend fun getUsers(): Response<User>
}