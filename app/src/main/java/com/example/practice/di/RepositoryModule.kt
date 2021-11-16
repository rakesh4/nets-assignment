package com.example.practice.di

import com.example.practice.repositories.UserDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserDetailsRepository(get(),get()) }



}