package com.matrimony.demo.di

import android.app.Application
import com.matrimony.demo.BaseApplication
import com.matrimony.demo.network.NetworkAPIService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    //We have to instruct the component how to build itself. We do this with the @Component.Builder annotation.
    open fun getApiInterface(): NetworkAPIService?

    @Component.Builder
    interface Builder {

        // Method #1
        fun build(): AppComponent

        // Method #2
        @BindsInstance
        fun application(application: Application): Builder
    }
}