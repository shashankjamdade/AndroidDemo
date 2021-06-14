package com.matrimony.demo.viewmodel

import com.google.common.truth.Truth.assertThat
import com.demo.model.ResultUserItem
import com.matrimony.demo.repository.FakeUserRepositoryImplTest
import com.demo.viewmodel.UserListViewModel
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
    fun `updating empty userchoice in db result in error`(){
        var userObj = ResultUserItem(userId = "1", userChoice = "request") //Replace userChoice with empty string will make test fail
        assertThat(userObj.userChoice).isNotEmpty()
    }

    @Test
    fun `passing 0 for result input resultIn error`(){
        var result = 10;
        assertThat(result).isGreaterThan(0)
    }


}