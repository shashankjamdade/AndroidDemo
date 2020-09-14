package com.matrimony.demo.di

import androidx.lifecycle.ViewModel
import com.matrimony.demo.viewmodel.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    // Method #1
//    @Binds
//    @IntoMap
//    @ViewModelKey(NoteViewModel::class)
//    abstract fun bindMainViewModel(notesViewModel: NoteViewModel): ViewModel
 // Method #1
    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserViewModel(notesViewModel: UserListViewModel): ViewModel
}
