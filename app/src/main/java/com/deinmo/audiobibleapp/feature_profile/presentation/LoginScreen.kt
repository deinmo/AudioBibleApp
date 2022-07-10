package com.deinmo.audiobibleapp.feature_profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.core.Screen


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
){
        Column(
            modifier = Modifier.padding(40.dp).fillMaxSize(),verticalArrangement = Arrangement.SpaceEvenly
        ) {
            var email by rememberSaveable{ mutableStateOf("")}
            TextField(value = email, onValueChange = { email = it}, label = {Text("email")},modifier = Modifier.padding(15.dp))
            var password by rememberSaveable{ mutableStateOf("")}
            TextField(value = password, onValueChange = {password = it}, label = {Text("password")},modifier = Modifier.padding(15.dp))
            Button(onClick = {
                if (viewModel.isSuccessful(email, password) == true)
                    navController.navigate(Screen.ProfileScreen.route)
                else
                    navController.navigate(Screen.SignUpScreen.route)
            },modifier = Modifier.padding(25.dp).fillMaxWidth()) {
                Text(text = "Login")
            }
                Text(text = "Don't have an account?")
                Button(onClick = {
                    navController.navigate(Screen.SignUpScreen.route)
                },modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                    Text(text = "Signin")
                }

        }
    }