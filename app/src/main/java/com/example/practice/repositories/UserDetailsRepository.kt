package com.example.practice.repositories

import com.example.practice.api.ApiService
import com.example.practice.models.User
import com.example.practice.utility.LocalizeTextProvider
import com.example.practice.api.Resource

class UserDetailsRepository(private val httpIService: ApiService,localizeTextProvider: LocalizeTextProvider) :BaseRepository(localizeTextProvider) {

    // api call for user list
    suspend fun executeGetUserData() : Resource<User> {
        return getResult {  httpIService.getUsers()}
    }
}