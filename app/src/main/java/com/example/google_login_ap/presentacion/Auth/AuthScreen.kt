package com.example.google_login_ap.presentacion.Auth
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(contentAlignment = Alignment.Center) {
            when {
                state.isLoading -> CircularProgressIndicator()

                state.user != null -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("¡Hola, ${state.user?.displayName}!", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { viewModel.processIntent(AuthIntent.SignOut) }) {
                            Text("Cerrar Sesión")
                        }
                    }
                }

                else -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Acceso Seguro", style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { viewModel.processIntent(AuthIntent.SignInWithGoogle(context)) }) {
                            Text("Iniciar sesión con Google")
                        }
                        if (state.errorMessage != null) {
                            Text(state.errorMessage!!, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}


