package com.example.practice.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.api.Resource
import com.example.practice.databinding.ActivityUserDetailsBinding
import com.example.practice.view_models.UserDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  class is used for show user list
 */

class UserDetailActivity : BaseActivity() {

    private lateinit var mBinding: ActivityUserDetailsBinding
    private lateinit var mAdapter: UserListAdapter
    private val mViewModel: UserDetailsViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        setViewModel()
        setAdapter()
        setObserver()

    }

    private fun setViewModel() {
        mBinding.lifecycleOwner = this
        mBinding.userDetailsVM = mViewModel
        mViewModel.fetchUsersList()
    }

    private fun setAdapter() {
        mBinding.recyclerMain.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = UserListAdapter( ArrayList(), mViewModel)
        mBinding.myAdapter = mAdapter

    }

    private fun setObserver() {
        mViewModel.getConsultations().observe(this, Observer { response ->
            when (response.status) {

                Resource.Status.LOADING -> showLoading()

                Resource.Status.SUCCESS ->{
                    hideLoading()
                    mViewModel.setReversedOrderedData()
                }

                Resource.Status.ERROR -> {
                    hideLoading()
                    showToast(response.message.toString())
                }
                else -> {
                    hideLoading()
                    showToast(response.message.toString())
                }
            }
        })

        mViewModel.getUserData().observe(this, Observer
        {
            it.let {
                mAdapter.setDataList(it)
            }
        })
    }
}