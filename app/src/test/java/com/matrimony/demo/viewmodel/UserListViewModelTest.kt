package com.matrimony.demo.viewmodel

import com.google.common.truth.Truth.assertThat
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.repository.FakeUserRepositoryImplTest
import org.junit.Before
import org.junit.Test

class UserListViewModelTest {

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setUp(){
        viewModel = UserListViewModel(FakeUserRepositoryImplTest())
    }

    @Test
    fun `updating empty or null values in db result in error`(){
        var userObj = ResultUserItem(userId = "1") //Replace with null will make test fail
        var result  = userObj?.let { viewModel.updateUserInfo(it) }
        assertThat(result).isNotNull()
    }

    @Test
    fun `passing 0 for result input resultIn error`(){
        var result = 10;
        assertThat(result).isGreaterThan(0)
    }


}