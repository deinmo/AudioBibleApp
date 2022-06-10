package com.deinmo.audiobibleapp.feature_profile.data.repository

import android.util.Log
import android.widget.Toast
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.Datadao
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.BibleApi
import com.deinmo.audiobibleapp.feature_profile.domain.model.UserProfile
import com.deinmo.audiobibleapp.feature_profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth?
): ProfileRepository {
    override fun signup(email: String, password: String): Boolean? {
        return try{
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful)
                        Log.d("success_message", "success")
                    else
                        Log.d("error_message", task.exception?.message!!)
                }
            true
        }catch (e: Exception){
            Log.d("error_message", e.message!!)
            false
        }
    }

    override fun login(password: String, email: String): Boolean? {
        return try{
            auth?.signInWithEmailAndPassword(email, password)
            true
        }catch (e: Exception){
            Log.d("error_message", e.message!!)
            false
        }
    }

    override suspend fun getprofile(): UserProfile? {
        return null
    }

    override fun getauth(): FirebaseAuth? {
        return auth
    }
}