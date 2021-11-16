package com.example.practice.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practice.api.RetrofitAPI
import com.example.practice.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsRepository(application: Context) {


    fun getUsersData(): MutableList<User> {

        var userData = mutableListOf<User>()
        RetrofitAPI.apiService.getUsers().enqueue(object : Callback<MutableList<User>> {
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
}