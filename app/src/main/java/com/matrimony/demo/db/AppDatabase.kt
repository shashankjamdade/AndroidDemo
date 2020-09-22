package com.matrimony.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse

@Database(entities = [ ResultUserItem::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}