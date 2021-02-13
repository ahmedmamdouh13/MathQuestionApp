package com.ahmedmamdouh13.ama.mathquestionapp.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LocationUtil @Inject constructor() {
    fun getLastKnownLocation(activity: Activity, onLocationFound: (Location?) -> Unit) {
      val  fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                onLocationFound(location)
            }


    }
}