package com.example.practice.repositories

import com.example.practice.api.ApiService
import com.example.practice.api.RetrofitAPI
import com.example.practice.models.User
import com.example.practice.utility.LocalizeTextProvider
import com.example.practice.utility.Resource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsRepository(private val httpIService: ApiService,localizeTextProvider: LocalizeTextProvider) :BaseRepository(localizeTextProvider) {


    fun getUsersData(): MutableList<User> {

        var userData = mutableListOf<User>()
        RetrofitAPI.apiService.getData().enqueue(object : Callback<MutableList<User>> {
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        userData = response.body()!!
                    }
                }

            }
        })
        return userData
    }

    suspend fun executeGetUserData() : Resource<User> {
        return getResult {  httpIService.getUsers()}
    }
}