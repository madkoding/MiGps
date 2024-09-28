package com.example.migps.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class LocationManager(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://migps-39e36-default-rtdb.firebaseio.com/").reference

    @SuppressLint("MissingPermission")
    fun getLocationAndSaveToDatabase(onLocationSaved: (latitude: Double, longitude: Double, timestamp: String) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val userId = auth.currentUser?.uid
                if (userId != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val timestamp = System.currentTimeMillis()

                    val locationData = mapOf(
                        "latitude" to latitude,
                        "longitude" to longitude,
                        "timestamp" to timestamp
                    )

                    database.child("users").child(userId).child("location").setValue(locationData)
                        .addOnSuccessListener {
                            // Formatear el timestamp a DD/MM/YYYY HH:MM:ss
                            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                            val formattedDate = dateFormat.format(Date(timestamp))

                            // Devolver los datos de latitud, longitud y timestamp formateado a la pantalla
                            onLocationSaved(latitude, longitude, formattedDate)
                        }
                        .addOnFailureListener {
                            // Manejo de error al guardar en la base de datos
                        }
                }
            }
        }
    }
}
