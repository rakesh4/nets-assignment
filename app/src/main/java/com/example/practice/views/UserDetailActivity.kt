package com.example.practice.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.practice.models.User
import com.example.practice.view_models.UserDetailsViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var mViewModel: UserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        setObserver()

    }

    private fun setViewModel() {
        mViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)
        mViewModel.fetchUsersList()
    }

    private fun setObserver() {

        mViewModel.userList.observe(this, Observer {
            val data = it
        // set adapter
        })

    }


}