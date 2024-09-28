package com.example.migps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.migps.data.AuthManager
import com.example.migps.data.LocationManager
import com.example.migps.ui.LoginScreen
import com.example.migps.ui.theme.MiGpsTheme

class MainActivity : ComponentActivity() {

    private var timestamp: String = ""
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private lateinit var authManager: AuthManager
    private lateinit var locationManager: LocationManager

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Variables para guardar los datos de la ubicación
        var latitude by mutableStateOf<Double?>(null)
        var longitude by mutableStateOf<Double?>(null)
        var timestamp by mutableStateOf<String?>(null)

        // Inicialización
        authManager = AuthManager(this)
        locationManager = LocationManager(this)

        // Configurar la interfaz de usuario
        setContent {
            MiGpsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), content = {
                    if (latitude != null && longitude != null && timestamp != null) {
                        // Mostrar latitud, longitud y timestamp en la pantalla
                        Text(text = "Latitud: $latitude\nLongitud: $longitude\nTimestamp: $timestamp")
                    } else {
                        LoginScreen(
                            authManager = authManager,
                            onLoginSuccess = {
                                // Verificar permisos y obtener la ubicación
                                checkLocationPermissions { lat, long, time ->
                                    latitude = lat
                                    longitude = long
                                    timestamp = time
                                }
                            }
                        )
                    }
                })
            }
        }
    }

    private fun checkLocationPermissions(onLocationSaved: (Double, Double, String) -> Unit) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            // Si ya se tienen los permisos, guarda la ubicación
            locationManager.getLocationAndSaveToDatabase(onLocationSaved)
        } else {
            // Si no, pedir los permisos
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        }
    }

    // Manejar la respuesta de los permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Si los permisos se concedieron, guardar la ubicación
            locationManager.getLocationAndSaveToDatabase { lat, long, time ->
                // Actualizar las variables para mostrar en la pantalla
                latitude = lat
                longitude = long
                timestamp = time
            }
        }
    }
}
