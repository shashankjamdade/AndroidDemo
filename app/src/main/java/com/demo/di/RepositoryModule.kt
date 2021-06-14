package com.demo.di

import com.demo.api.NetworkAPIService
import com.demo.db.dao.UserDao
import com.demo.listener.UserRepository
import com.demo.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideDataRepository(apiService: NetworkAPIService, userDao: UserDao): UserRepository {
        return UserRepositoryImpl(apiService, userDao) as UserRepository
    }

}