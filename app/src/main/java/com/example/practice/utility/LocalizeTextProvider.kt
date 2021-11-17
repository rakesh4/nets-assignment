package com.example.practice.utility

import android.content.Context
import com.example.practice.R

/**
 * This class provide string messages
 */
class LocalizeTextProvider(private val context: Context) {

    fun getNoInternetMessage(): String {
        return context.getString(R.string.no_internet)
    }

    fun getSomethingWrongMessage(): String {
        return context.getString(R.string.something_went_wrong)
    }





}