package com.example.migps.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.migps.data.AuthManager

/**
 * LoginScreen: Pantalla de inicio de sesión que permite al usuario autenticarse.
 * @param authManager Instancia de AuthManager para manejar la autenticación.
 * @param onLoginSuccess Callback que se ejecuta cuando el usuario se autentica correctamente.
 */
@Composable
fun LoginScreen(
    authManager: AuthManager,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginStatus by remember { mutableStateOf("Not Logged In") }

    fun loginUser() {
        authManager.login(email, password,
            onSuccess = {
                loginStatus = "Login Successful"
                onLoginSuccess()
            },
            onFailure = { errorMessage ->
                loginStatus = "Login Failed: $errorMessage"
            }
        )
    }

    // UI del formulario de login
    Scaffold {
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            Button(
                onClick = { loginUser() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Login")
            }
            Text(text = loginStatus, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(authManager = AuthManager(null), onLoginSuccess = {})
}
