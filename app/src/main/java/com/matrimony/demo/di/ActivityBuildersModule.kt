package com.matrimony.demo.di

import com.matrimony.demo.ui.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class,FragmentBuildersModule::class])
    abstract fun contributeUserListActivity(): UserListActivity

}