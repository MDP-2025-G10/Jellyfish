package com.example.mdp.navigation

sealed class NavRoutes(val route: String) {

    data object RouteToAuth : NavRoutes("Auth")
    data object RouteToLogin : NavRoutes("Login")
    data object RouteToRegister : NavRoutes("Register")
    data object RouteToHome : NavRoutes("Home")
    data object RouteToFood : NavRoutes("Food")
    data object RouteToNutrition : NavRoutes("Nutrition")
    data object RouteToWorkout : NavRoutes("Workout")
    data object RouteToProfile : NavRoutes("Profile")
    data object RouteToCamera : NavRoutes("Camera")
    data object RouteToCalendar : NavRoutes("Calendar")
    data object RouteToSetting : NavRoutes("Setting")

//    companion object {
//        fun routeToMain(date: String): String = "main/$date"
//        fun routeToExerciseList(bodyPart: String): String = "exercise/bodyPart/$bodyPart"
//        fun routeToExerciseDetail(exerciseId: String): String = "exerciseDetail/$exerciseId"
//    }
}