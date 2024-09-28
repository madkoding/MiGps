package com.example.migps.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth

/**
 * AuthManager: Clase encargada de manejar la autenticación con Firebase.
 * @param context Contexto de la actividad.
 */
class AuthManager(private val context: Context?) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Función para iniciar sesión en Firebase usando correo y contraseña.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param onSuccess Callback en caso de éxito.
     * @param onFailure Callback en caso de error.
     */
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Unknown error")
                }
            }
    }
}
