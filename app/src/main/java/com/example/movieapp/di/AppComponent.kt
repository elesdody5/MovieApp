package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModuleBinds::class,ViewModelBuilderModule::class,AppModuleProvider::class,HomeViewModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
    fun inject(homeFragment: HomeFragment)
}