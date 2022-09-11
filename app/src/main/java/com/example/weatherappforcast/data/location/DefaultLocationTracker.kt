package com.example.weatherappforcast.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weatherappforcast.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    @ApplicationContext val context:  Context
):LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
       val coarseLocation = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val fineAccessLocation = ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
        val locationManger = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnable = locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!coarseLocation||!fineAccessLocation||!isGpsEnable){
            return null
        }
        return suspendCancellableCoroutine { cont ->
            fusedLocationClient.lastLocation.apply {
                if (isComplete){
                    if (isSuccessful){
                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    cont.resume(it)
                }

                addOnFailureListener {
                    cont.resume(null)
                }

                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}