package com.matrimony.demo.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse

@Dao
interface UserDao {

    @Query("SELECT * FROM ResultUserItem")
    fun loadUserList(): LiveData<List<ResultUserItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userListResponse: ResultUserItem)

    @Update
    fun updateUser(user: ResultUserItem)
}
