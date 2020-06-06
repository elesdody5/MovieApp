package com.example.movieapp.di


import com.example.movieapp.data.repository.MovieGetway
import com.example.movieapp.data.repository.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun bindRepository(repo: Repository): MovieGetway

}
