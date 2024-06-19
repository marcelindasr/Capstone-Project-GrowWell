package com.example.growwell.ui.peta

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.growwell.R
import com.example.growwell.api.PlacesApiService
import com.example.growwell.api.PlacesResponse
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetaFragment : Fragment() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val REQUEST_CHECK_SETTINGS = 2
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesApiService: PlacesApiService
    private var googleMap: GoogleMap? = null

    private val callback = OnMapReadyCallback { map ->
        googleMap = map
        // Meminta izin lokasi jika diperlukan
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap?.isMyLocationEnabled = true
            checkLocationSettings()
        } else {
            requestLocationPermission()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inisialisasi Places API
        Places.initialize(requireContext(), getString(R.string.google_maps_key))
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Inisialisasi Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        placesApiService = retrofit.create(PlacesApiService::class.java)

        return inflater.inflate(R.layout.fragment_peta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Anda bisa menampilkan penjelasan kepada pengguna mengapa izin ini diperlukan
            // Misalnya dengan menggunakan dialog
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Izin diberikan, lakukan tindakan yang memerlukan izin ini
                googleMap?.isMyLocationEnabled = true
                checkLocationSettings()
            } else {
                // Izin ditolak, Anda bisa menampilkan pesan kepada pengguna
            }
        }
    }

    private fun checkLocationSettings() {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            getCurrentLocation()
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(requireActivity(), REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission()
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                googleMap?.addMarker(MarkerOptions().position(currentLatLng).title("Lokasi Anda"))
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                showNearbyHospitals(currentLatLng)
            }
        }
    }

    private fun showNearbyHospitals(currentLatLng: LatLng) {
        val location = "${currentLatLng.latitude},${currentLatLng.longitude}"
        val radius = 5000 // 5 km radius
        val type = "hospital"
        val apiKey = getString(R.string.google_maps_key)

        val call = placesApiService.getNearbyPlaces(location, radius, type, apiKey)
        call.enqueue(object : Callback<PlacesResponse> {
            override fun onResponse(call: Call<PlacesResponse>, response: Response<PlacesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.forEach { place ->
                        val placeLatLng = LatLng(place.geometry.location.lat, place.geometry.location.lng)
                        googleMap?.addMarker(MarkerOptions().position(placeLatLng).title(place.name))
                    }
                }
            }

            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                Log.e("PetaFragment", "Failed to get nearby places: ${t.message}")
            }
        })
    }
}
