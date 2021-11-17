package com.example.practice.views

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.utility.CustomProgressBar


abstract class BaseActivity :AppCompatActivity () {

    private var mProgressDialog: CustomProgressBar? = null
    lateinit var context: Context



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
    }

    protected fun showToast(msg: String){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    protected fun showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = CustomProgressBar(this)
        }
        if (!mProgressDialog?.isShowing!!) {
            mProgressDialog?.show()
        }
    }

    open fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            mProgressDialog?.dismiss()
        }
    }








}
