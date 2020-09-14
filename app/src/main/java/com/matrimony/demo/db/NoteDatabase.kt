package com.matrimony.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse

// - Database Class
@Database(entities = [/*Note::class,*/ ResultUserItem::class],version = 1)
abstract class NoteDatabase: RoomDatabase() {
//    abstract fun noteDao(): NoteDao
    abstract fun userDao(): UserDao
}