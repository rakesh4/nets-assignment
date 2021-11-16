package com.example.practice.di

import com.example.practice.utility.LocalizeTextProvider
import com.example.practice.utility.NetworkManager
import org.koin.dsl.module


val UtilsManagerModule = module {
    single { NetworkManager(get()) }
    single { LocalizeTextProvider(get()) }

}