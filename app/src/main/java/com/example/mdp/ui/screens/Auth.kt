package com.example.mdp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mdp.navigation.NavRoutes
import com.example.mdp.ui.components.auth.AppLogo
import com.example.mdp.ui.components.auth.AuthButton
import com.example.mdp.ui.components.auth.AuthInput
import com.example.mdp.ui.components.auth.SignInButton
import com.example.mdp.firebase.auth.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun Auth(
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel(),
    isLogin: Boolean
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            AppLogo(120)
            AuthInput(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                keyboardType = KeyboardType.Email
            )

            AuthInput(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            AuthButton(text = if (isLogin) "Login" else "Register") {
                if (isLogin) {
                    authViewModel.login(email, password)
                } else {
                    authViewModel.register(email, password)
                }
                email = ""
                password = ""
            }

            TextButton(onClick = {
                val targetRoute =
                    if (isLogin) NavRoutes.RouteToRegister.route else NavRoutes.RouteToLogin.route
                navController.navigate(targetRoute)
            }) {
                Text(
                    text = if (isLogin) "Don't have an account? Register" else "Already have an account? Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            SignInButton { authViewModel.signInWithGoogle() }
        }
    }

}


