package com.example.practice.view_models

import android.app.Application
import androidx.lifecycle.*
import com.example.practice.models.User
import com.example.practice.repositories.UserDetailsRepository
import com.example.practice.utility.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class UserDetailsViewModel (private val userDetailsRepository: UserDetailsRepository) : ViewModel() {

    private var userList = MutableLiveData<User>()
//    val userList: LiveData<List<User>>
//        get() = _userList



    fun fetchUsersList()  {
       // if (Utils.isNetworkConnected(mApplication.applicationContext)) {
          viewModelScope.launch {
              userList.postValue(userDetailsRepository.executeGetUserData().data)
          }

//        viewModelScope.launch {
//            userDetailsRepository.getUsersData()
//        }

//          }
       // userList.value = userDetailsRepository.executeGetUserData().data.user
           // val data = _userList.value

//        } else {
//            //_onMessageError.value = true
//        }

      //  return userList

    }

    fun getConsultations(): LiveData<User> {
        return userList
    }
}