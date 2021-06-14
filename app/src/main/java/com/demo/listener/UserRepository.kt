package com.demo.listener

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.model.ResultUserItem
import com.demo.model.UserListResponse

interface UserRepository {

    fun fetchAllUsers(result:Int): MutableLiveData<UserListResponse>

    fun insertUserList(userRes: MutableList<ResultUserItem>)

    fun updateUser(userRes: ResultUserItem)

    fun getAllUsersFromDb(): LiveData<List<ResultUserItem>>
}