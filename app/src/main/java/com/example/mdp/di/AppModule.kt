package com.example.mdp.di


import com.example.mdp.usda.repository.FoodRepository
import com.example.mdp.usda.RetrofitInstance
import com.example.mdp.data.database.MealDatabase
import com.example.mdp.data.database.WorkoutDatabase
import com.example.mdp.data.repository.MealRepository
import com.example.mdp.data.repository.WorkoutRepository
import com.example.mdp.firebase.repository.AuthRepository
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import com.example.mdp.usda.viewmodel.FoodViewModel
import com.example.mdp.viewmodels.MealViewModel
import com.example.mdp.viewmodels.WorkoutViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() } // Provide FirebaseAuth instance
    single { AuthRepository(get(), androidContext()) }   //  Inject FirebaseAuth and context into AuthRepository
    viewModel { AuthViewModel(get()) }  // Inject AuthRepository into AuthViewModel

    single { WorkoutDatabase.getDatabase(get()).workoutDao() }
    single { WorkoutRepository(get()) }
    viewModel { WorkoutViewModel(get()) }

    single { MealDatabase.getDatabase(get()).mealDao() }
    single { MealRepository(get()) }
    viewModel { MealViewModel(get()) }

    // USDAApiService
    single { RetrofitInstance.api }
    single { FoodRepository(get()) }
    viewModel { FoodViewModel(get()) }

}