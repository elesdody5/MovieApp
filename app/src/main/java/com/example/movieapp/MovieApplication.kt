package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.AppComponent
import com.example.movieapp.di.DaggerAppComponent

class MovieApplication:Application() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

     fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(this)
    }


}
