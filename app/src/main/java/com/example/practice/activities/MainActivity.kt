package com.example.practice.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.R
import com.example.practice.api.RetrofitAPI
import com.example.practice.response.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Display list of users with the user information mentioned in the assignment
// Note: A nice looking UI is appreciated but clean code is more important

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun getUsersData() {
        RetrofitAPI.apiService.getUsers().enqueue(object : Callback<MutableList<User>> {
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {

            }
        })
    }


}
