package com.example.movieapp.di

import android.app.Application
import com.example.movieapp.ui.download.DownloadFragment
import com.example.movieapp.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModuleBinds::class, ViewModelBuilderModule::class, AppModuleProvider::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(downloadFragment: DownloadFragment)
}