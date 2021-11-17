package com.example.practice

import android.app.Application
import com.example.practice.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *  Dependency injection initialized
 */
class MainApp: Application() {

    companion object {
        private var INSTANCE: MainApp? = null
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(httpModule,UtilsManagerModule,viewModelModule,repositoryModule))
        }

    }
}