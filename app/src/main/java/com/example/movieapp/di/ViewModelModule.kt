package com.example.movieapp.di

import androidx.lifecycle.ViewModel
import com.example.movieapp.ui.download.DownloadViewModel
import com.example.movieapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewmodel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DownloadViewModel::class)
    abstract fun bindDownloadViewModel(viewmodel: DownloadViewModel): ViewModel
}
