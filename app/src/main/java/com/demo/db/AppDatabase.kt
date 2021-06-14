package com.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.db.dao.UserDao
import com.demo.model.ResultUserItem

@Database(entities = [ ResultUserItem::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}