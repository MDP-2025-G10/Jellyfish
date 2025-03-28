package com.example.mdp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.liveData
import com.example.mdp.data.model.Workouts
import com.example.mdp.data.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class WorkoutViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {

    // LiveData to observe meal data
    val allWorkouts: LiveData<List<Workouts>> = liveData(Dispatchers.IO) {
        emit(workoutRepository.getAllWorkouts())
    }

    // Insert a meal into the database
    fun insertWorkout(workout: Workouts) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.insertWorkout(workout)
        }
    }

    // Delete a meal from the database
    fun deleteWorkout(workout: Workouts) {
        viewModelScope.launch(Dispatchers.IO) {
           workoutRepository.deleteWorkout(workout)
        }
    }
    fun insertTestWorkout() = viewModelScope.launch {
       workoutRepository.insertTestWorkout()
    }
}