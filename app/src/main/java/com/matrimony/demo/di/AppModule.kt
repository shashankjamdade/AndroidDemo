package com.matrimony.demo.di

import android.app.Application
import androidx.room.Room
import com.matrimony.demo.db.dao.UserDao
import com.matrimony.demo.db.NoteDatabase
import com.matrimony.demo.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


//Provide all the app level dependency here
@Module
class AppModule {


    // Method #1
    @Singleton
    @Provides
    fun providesAppDatabase(app:Application): NoteDatabase {
        return Room.databaseBuilder(app,
            NoteDatabase::class.java,"demoapp_db").build()
    }

//     Method #2
//    @Singleton
//    @Provides
//    fun providesNoteDao(db: NoteDatabase): NoteDao {
//        return db.noteDao()
//    }

    // Method #2
    @Singleton
    @Provides
    fun providesUserDao(db: NoteDatabase): UserDao {
        return db.userDao()
    }

//     Method #3
//    @Provides
//    fun providesRepository(noteDao: NoteDao):NoteRepository{
//        return NoteRepository(noteDao)
//    }
 // Method #3
    @Provides
    fun providesUserRepository(userDao: UserDao):UserRepository{
        return UserRepository(userDao)
    }
}