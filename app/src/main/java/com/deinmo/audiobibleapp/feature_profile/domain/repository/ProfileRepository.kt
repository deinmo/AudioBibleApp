package com.deinmo.audiobibleapp.feature_profile.domain.repository

import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto.BooksDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.Chaptersdto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.Datadto
import com.deinmo.audiobibleapp.feature_profile.domain.model.UserProfile
import com.google.firebase.auth.FirebaseAuth

interface ProfileRepository {
    fun signup(email: String,password: String): Boolean?

    fun login(password: String, email: String): Boolean?

    suspend fun getprofile(): UserProfile?

    fun getauth(): FirebaseAuth?
}