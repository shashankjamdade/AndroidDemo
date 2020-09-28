package com.matrimony.demo.di

import android.app.Application
import androidx.room.Room
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.db.AppDatabase
import com.matrimony.demo.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesAppDatabase(app:Application): AppDatabase {
        return Room.databaseBuilder(app,
            AppDatabase::class.java,"demoapp_db").build()
    }

    @Singleton
    @Provides
    fun providesUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

}