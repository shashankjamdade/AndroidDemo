package com.matrimony.demo.di

import com.matrimony.demo.api.NetworkAPIService
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideDataRepository(apiService: NetworkAPIService, userDao:UserDao): UserRepository {
        return UserRepository(apiService, userDao)
    }

}