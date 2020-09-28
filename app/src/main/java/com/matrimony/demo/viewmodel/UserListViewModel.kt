package com.matrimony.demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.matrimony.demo.listener.UserRepository
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse
import com.matrimony.demo.repository.UserRepositoryImpl

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