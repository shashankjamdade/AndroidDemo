package com.matrimony.demo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse
import kotlinx.coroutines.*
import javax.inject.Inject

class UserRepository @Inject constructor(val userDao: UserDao) {

    // Method #1
    //function to insert note in database
    fun insert(userRes: ResultUserItem) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insertUserList(userRes)
        }
    }

    fun updateUser(userRes: ResultUserItem) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.updateUser(userRes)
        }
    }

    // Method #5
    //function to get all notes in database
    fun getAllUsers(): LiveData<List<ResultUserItem>> {
        return userDao.loadUserList()
    }
}