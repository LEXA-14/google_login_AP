package com.example.google_login_ap.di
import android.content.Context
import androidx.credentials.CredentialManager
import com.example.google_login_ap.data.repository.AuthRepositoryImpl
import com.example.google_login_ap.domain.auth.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager =
        CredentialManager.create(context)

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        credentialManager: CredentialManager
    ): AuthRepository = AuthRepositoryImpl(auth, credentialManager)
}
