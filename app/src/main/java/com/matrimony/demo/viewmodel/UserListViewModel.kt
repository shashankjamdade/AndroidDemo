package com.matrimony.demo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse
import com.matrimony.demo.repository.UserRepository

class UserListViewModel  @ViewModelInject constructor(
    val userRepository: UserRepository
) : ViewModel(), LifecycleObserver {

    private val errorOnAPI = MutableLiveData<String>()
    var userListMutableLiveData = MutableLiveData<UserListResponse>()

    fun fetchUsersFromDb(): LiveData<List<ResultUserItem>> {
        return userRepository.getAllUsersFromDb();
    }


    fun fetchUserListInfo(results: Int): MutableLiveData<UserListResponse> {
        return userRepository?.fetchAllUsers(results)
    }

    fun updateUserInfo(usr: ResultUserItem) {
        userRepository.updateUser(usr);
    }

    fun fetchError(): LiveData<String> = errorOnAPI
    fun fetchUsersLiveData(): LiveData<UserListResponse> = userListMutableLiveData

}