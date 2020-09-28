package com.matrimony.demo.listener

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse

interface UserRepository {

    fun fetchAllUsers(result:Int): MutableLiveData<UserListResponse>

    fun insertUserList(userRes: MutableList<ResultUserItem>)

    fun updateUser(userRes: ResultUserItem)

    fun getAllUsersFromDb(): LiveData<List<ResultUserItem>>
}