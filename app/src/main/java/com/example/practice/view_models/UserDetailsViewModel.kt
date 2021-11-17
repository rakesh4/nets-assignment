package com.example.practice.view_models

import androidx.lifecycle.*
import com.example.practice.api.Resource
import com.example.practice.models.User
import com.example.practice.models.UserDataItem
import com.example.practice.repositories.UserDetailsRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val userDetailsRepository: UserDetailsRepository) : ViewModel() {

    private var userListResponse = MutableLiveData<Resource<User>>()
    private var userData = MutableLiveData<List<UserDataItem>>()


    fun fetchUsersList() {
        userListResponse.value = (Resource.loading(null))
        viewModelScope.launch {
            userListResponse.postValue(userDetailsRepository.executeGetUserData())
        }

    }

    // get reversed user list
    fun setReversedOrderedData() {
        userData.value = userListResponse.value?.data?.reversed()
    }

    fun getConsultations(): LiveData<Resource<User>> {
        return userListResponse
    }

    // row clicked action
    fun onExcludeByIdClicked(item: UserDataItem) {
        val userList = userListResponse.value?.data?.reversed()
         userData.value = userList?.filter {
            it.id != item.id
        }
    }

    fun getUserData(): LiveData<List<UserDataItem>> {
        return userData
    }
}