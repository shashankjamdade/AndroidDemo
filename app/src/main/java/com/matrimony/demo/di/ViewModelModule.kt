package com.matrimony.demo.di

import androidx.lifecycle.ViewModel
import com.matrimony.demo.viewmodel.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserViewModel(notesViewModel: UserListViewModel): ViewModel
}
