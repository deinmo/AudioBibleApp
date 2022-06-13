package com.deinmo.audiobibleapp.feature_profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.core.Screen


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
){

    Box() {
        Column {
            var email by rememberSaveable{ mutableStateOf("")}
            TextField(value = email, onValueChange = { email = it}, label = {Text("email")})
            var password by rememberSaveable{ mutableStateOf("")}
            TextField(value = password, onValueChange = {password = it}, label = {Text("password")})
            Button(onClick = {
                if (viewModel.isSuccessful(email, password) == true)
                    navController.navigate(Screen.ProfileScreen.route)
                else
                    navController.navigate(Screen.SignUpScreen.route)
            }) {
                Text(text = "Login")
            }
            Row() {
                Text(text = "Don't have an account?")
                Button(onClick = {
                    navController.navigate(Screen.SignUpScreen.route)
                }) {
                    Text(text = "Signin")
                }
            }
        }
    }
}