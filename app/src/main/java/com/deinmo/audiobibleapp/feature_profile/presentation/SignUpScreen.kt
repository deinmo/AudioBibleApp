package com.deinmo.audiobibleapp.feature_profile.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
){
    var context  = LocalContext.current
    Box() {
        Column() {
            var email by rememberSaveable {
                mutableStateOf("")
            }
            TextField(value = email, onValueChange = {email = it}, label = {Text("email")})
            var password by rememberSaveable {
                mutableStateOf("")
            }
            TextField(value = password, onValueChange = {password = it}, label = {Text("password")})
            TextField(value = "", onValueChange = {})
            TextField(value = "", onValueChange = {})
            Button(onClick = {
                if(viewModel.isSuccessful(email,password) == true)
                    Toast.makeText(context,"Signed up successfully",Toast.LENGTH_LONG)
            }) {
                Text(text = "Signup")
            }
        }
}
}