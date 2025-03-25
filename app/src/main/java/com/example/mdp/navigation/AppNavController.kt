package com.example.mdp.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mdp.ui.screens.Auth
import com.example.mdp.ui.screens.Calendar
import com.example.mdp.ui.screens.Camera
import com.example.mdp.ui.screens.Food
import com.example.mdp.ui.screens.Home
import com.example.mdp.ui.screens.Nutrition
import com.example.mdp.ui.screens.Profile
import com.example.mdp.ui.screens.Setting
import com.example.mdp.ui.screens.Workout
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AppNavController() {

    val authViewModel: AuthViewModel = koinViewModel()
    val navController = rememberNavController()
    val currentUser by authViewModel.currentUser.observeAsState()

    Log.d("AppNavController", "Current user: $currentUser")

    NavHost(
        navController = navController,
        startDestination =
        if (currentUser == null) NavRoutes.RouteToLogin.route
        else NavRoutes.RouteToHome.route
    ) {
        composable(
            route = NavRoutes.RouteToLogin.route,
        ) { Auth(navController, authViewModel, isLogin = true) }

        composable(
            route = NavRoutes.RouteToRegister.route,
        ) { Auth(navController, authViewModel, isLogin = false) }

        composable(
            route = NavRoutes.RouteToHome.route,
        ) { Home(navController) }

        composable(
            route = NavRoutes.RouteToFood.route,
        ) { Food(navController) }

        composable(
            route = NavRoutes.RouteToNutrition.route,
        ) { Nutrition(navController) }

        composable(
            route = NavRoutes.RouteToWorkout.route,
        ) { Workout(navController) }

        composable(
            route = NavRoutes.RouteToCamera.route,
        ) { Camera(navController) }

        composable(
            route = NavRoutes.RouteToCalendar.route,
        ) { Calendar(navController) }

        composable(
            route = NavRoutes.RouteToSetting.route,
        ) { Setting(navController) }

        composable(
            route = NavRoutes.RouteToProfile.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) { Profile(navController) }

    }
}