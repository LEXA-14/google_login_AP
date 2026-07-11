package com.example.google_login_ap.presentacion.Auth

import android.content.Context

sealed class AuthIntent {
    data class SignInWithGoogle(val context: Context) : AuthIntent()
    object SignOut : AuthIntent()
}