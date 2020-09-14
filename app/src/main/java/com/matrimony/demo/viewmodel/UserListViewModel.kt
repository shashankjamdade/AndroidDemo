package com.matrimony.demo.viewmodel

import androidx.lifecycle.*
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse
import com.matrimony.demo.network.NetworkAPIService
import com.matrimony.demo.repository.UserRepository
import com.matrimony.demo.util.CommonUtils
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    var apiService: NetworkAPIService?,
    val userRepository: UserRepository
) : ViewModel(), LifecycleObserver {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val errorOnAPI = MutableLiveData<String>()
    var userListMutableLiveData = MutableLiveData<UserListResponse>()


    fun fetchUserListInfo(results: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = apiService?.fetchUsers(results)
                if (response?.isSuccessful!!) {
                    response?.body()?.results?.forEachIndexed { index, resultUserItem ->
                        resultUserItem?.userId = index?.toString()
                        userRepository.insert(resultUserItem)
                        CommonUtils.printLog("INTERATE","${resultUserItem?.userId}")
                    }
                    userListMutableLiveData.postValue(response?.body())
                    loading.postValue(false)
                } else {
                    loading.postValue(false)
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            } catch (e: Exception) {
                loading.postValue(false)
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }

        }
    }

    fun updateUserInfo(usr:ResultUserItem){
        userRepository.updateUser(usr);
    }

    fun fetchError(): LiveData<String> = errorOnAPI
    fun fetchUsersLiveData(): LiveData<UserListResponse> = userListMutableLiveData

}