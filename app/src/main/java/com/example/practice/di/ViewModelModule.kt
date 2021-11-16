package com.example.practice.di

import com.example.practice.view_models.UserDetailsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { UserDetailsViewModel(get()) }
}