package com.deinmo.audiobibleapp.feature_profile.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
){
    var context  = LocalContext.current
        Column( modifier = Modifier.padding(40.dp).fillMaxSize(),verticalArrangement = Arrangement.SpaceEvenly) {
            var email by rememberSaveable {
                mutableStateOf("")
            }
            TextField(value = email, onValueChange = {email = it}, label = {Text("email")},modifier = Modifier.padding(15.dp))
            var password by rememberSaveable {
                mutableStateOf("")
            }
            TextField(value = password, onValueChange = {password = it}, label = {Text("password")},modifier = Modifier.padding(15.dp))
    //        TextField(value = "", onValueChange = {})
    //        TextField(value = "", onValueChange = {})
            Button(onClick = {
                if(viewModel.isSuccessful(email,password) == true)
                    Toast.makeText(context,"Signed up successfully",Toast.LENGTH_LONG)
                else
                    Toast.makeText(context,"Please try again",Toast.LENGTH_LONG)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Signup")
            }
        }
}
