package com.example.google_login_ap.presentacion.Auth

import androidx.lifecycle.ViewModel

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    init { checkSession() }

    private fun checkSession() {
        authRepository.getCurrentUser()?.let { user ->
            _state.update { it.copy(user = user) }
        }
    }

    fun processIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.SignInWithGoogle -> signIn(intent.context)
            is AuthIntent.SignOut -> signOut()
        }
    }

    private fun signIn(context: Context) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authRepository.signInWithGoogle(context)

            result.fold(
                onSuccess = { user -> _state.update { it.copy(isLoading = false, user = user) } },
                onFailure = { e -> _state.update { it.copy(isLoading = false, errorMessage = e.message) } }
            )
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            _state.update { it.copy(user = null) }
        }
    }
}
