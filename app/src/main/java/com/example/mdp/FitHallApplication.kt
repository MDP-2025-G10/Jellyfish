package com.example.mdp

import android.app.Application
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import com.example.mdp.di.appModule
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class FitHallApplication : Application() {




    override fun onCreate() {
        super.onCreate()

        Log.d("FitHallApplication", "Application onCreate started")

        // Initialize Firebase
        try {
            FirebaseApp.initializeApp(this)
            Log.d("FitHallApplication", "Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e("FitHallApplication", "Error initializing Firebase: ${e.message}")
            return // Exit if Firebase initialization fails
        }

        // Log all initialized FirebaseApp instances
        val apps = FirebaseApp.getApps(this)
        Log.d("FitHallApplication", "Number of FirebaseApp instances: ${apps.size}")
        for (app in apps) {
            Log.d("FitHallApplication", "FirebaseApp: ${app.name}, ${app.options}")
        }

        // Test FirebaseAuth
        try {
            val firebaseAuth = FirebaseAuth.getInstance()
            Log.d("FitHallApplication", "FirebaseAuth initialized: $firebaseAuth")
        } catch (e: Exception) {
            Log.e("FitHallApplication", "Error getting FirebaseAuth instance: ${e.message}")
        }

        startKoin {
            androidContext(this@FitHallApplication)
            modules(appModule)
        }

        Log.d("FitHallApplication", "Koin started successfully")


    }

}