package com.example.practice.view_models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.practice.api.Resource
import com.example.practice.models.User
import com.example.practice.models.UserDataItem
import com.example.practice.repositories.UserDetailsRepository
import com.example.practice.utility.LocalizeTextProvider
import com.example.practice.utility.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserDetailsViewModelTest {

    private lateinit var repository: UserDetailsRepository
    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var localizeTextProvider: LocalizeTextProvider


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<User>>
    @Mock
    private lateinit var apiUsersDataObserver: Observer<List<UserDataItem>>

    @Before
    fun setup() {
        localizeTextProvider = mock()
        repository = mock()
        viewModel = UserDetailsViewModel(repository)
    }

    @Test
    fun test_objects_not_null() {
        Assert.assertNotNull(repository)
        Assert.assertNotNull(viewModel)
        Assert.assertNotNull(localizeTextProvider)
    }

    @Test
    fun `getUserData()_return_value`() {
        Assert.assertNotNull(viewModel.getUserData())
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(apiUsersObserver)
                .`when`(repository)
                .executeGetUserData()
            viewModel.getUserData().observeForever(apiUsersDataObserver)
            viewModel.getUserData().removeObserver(apiUsersDataObserver)
        }
    }



}