package com.example.google_login_ap.presentacion.Auth

import com.google.firebase.auth.FirebaseUser

data class AuthState(
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null,
    val errorMessage: String? = null
)