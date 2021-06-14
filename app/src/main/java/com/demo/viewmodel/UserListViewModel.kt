package com.demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.demo.listener.UserRepository
import com.demo.model.ResultUserItem
import com.demo.model.UserListResponse

class UserListViewModel  @ViewModelInject constructor(
    val userRepositoryImpl: UserRepository
) : ViewModel() {

    fun fetchUsersFromDb(): LiveData<List<ResultUserItem>> {
        return userRepositoryImpl.getAllUsersFromDb();
    }


    fun fetchUserListInfo(results: Int): MutableLiveData<UserListResponse> {
        return userRepositoryImpl?.fetchAllUsers(results)
    }

    fun updateUserInfo(usr: ResultUserItem) {
        userRepositoryImpl.updateUser(usr);
    }

}