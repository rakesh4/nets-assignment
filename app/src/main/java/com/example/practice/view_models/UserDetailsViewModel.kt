package com.example.practice.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practice.models.User
import com.example.practice.repositories.UserDetailsRepository
import com.example.practice.utility.Utils

class UserDetailsViewModel (application: Application) : AndroidViewModel(application) {

    private var mApplication: Application = application
    private val mRepository = UserDetailsRepository(application.applicationContext)
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList


    fun fetchUsersList() {
        if (Utils.isNetworkConnected(mApplication.applicationContext)) {
            _userList.value = mRepository.getUsersData()

        } else {
            //_onMessageError.value = true
        }

    }
}