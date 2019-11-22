package com.wazunga.findit.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.*
import com.google.gson.Gson
import com.wazunga.findit.R
import com.wazunga.findit.utils.RequestKeys
import com.wazunga.nhulox97.findit.models.Result
import com.wazunga.nhulox97.findit.utils.Network

class CategoryActivity : AppCompatActivity() {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var cvHospitals: CardView
    private lateinit var cvSchools: CardView
    private lateinit var cvRestaurants: CardView
    private lateinit var cvPharmacies: CardView
    private var lat: Double = 0.0
    private var long: Double = 0.0

    //private val queue = Volley.newRequestQueue(this)
    private val rKeys = RequestKeys()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_categorias)

        initComponents()

        cvRestaurants.setOnClickListener {
            showPlaces(rKeys.RESTAURANT_CATEGORY)
        }

        cvSchools.setOnClickListener {
            showPlaces(rKeys.SCHOOL_CATEGORY)
        }

        cvPharmacies.setOnClickListener {
            showPlaces(rKeys.PHARMACIES_CATEGORY)
        }

        cvHospitals.setOnClickListener {
            showPlaces(rKeys.HOSPITAL_CATEGORY)
        }
    }

    private fun initComponents() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        cvHospitals = findViewById(R.id.cv_hospitals)
        cvPharmacies = findViewById(R.id.cv_pharmacies)
        cvSchools = findViewById(R.id.cv_schools)
        cvRestaurants = findViewById(R.id.cv_restaurants)
    }

    private fun showPlaces(type: String) {
        val r: String
        val distance = 2000
        when (type) {
            rKeys.HOSPITAL_CATEGORY -> {
                r = "${rKeys.REQUEST}location=$lat,$long&radius=$distance&" +
                        "type=${rKeys.HOSPITAL_CATEGORY}&key=${rKeys.KEY}"
                getNearbyPlacesByCurrentLocation(r)
            }
            rKeys.PHARMACIES_CATEGORY -> {
                r = "${rKeys.REQUEST}location=$lat,$long&radius=$distance&" +
                        "type=${rKeys.PHARMACIES_CATEGORY}&key=${rKeys.KEY}"
                getNearbyPlacesByCurrentLocation(r)
            }
            rKeys.RESTAURANT_CATEGORY -> {
                r = "${rKeys.REQUEST}location=$lat,$long&radius=$distance&" +
                        "type=${rKeys.RESTAURANT_CATEGORY}&key=${rKeys.KEY}"
                getNearbyPlacesByCurrentLocation(r)
            }
            rKeys.SCHOOL_CATEGORY -> {
                r = "${rKeys.REQUEST}location=$lat,$long&radius=$distance&" +
                        "type=${rKeys.SCHOOL_CATEGORY}&key=${rKeys.KEY}"
                getNearbyPlacesByCurrentLocation(r)
            }
        }
    }

    private fun getNearbyPlacesByCurrentLocation(uri: String) {
        if (Network.networkExists(this)) {
            if (isLocationEnabled()) {
                val queue = Volley.newRequestQueue(this)

                val request = StringRequest(Request.Method.GET, uri, Response.Listener { res ->
                    try {
                        Log.d("VolleyRequest", res)
                        startActivityByResponseStatus(res)
                    } catch (e: Exception) {
                        Log.e("ExceptionVolley", e.toString())
                    }
                }, Response.ErrorListener { error -> Log.e("ErrorVolley", error.toString()) })
                queue.add(request)
            } else
                Toast.makeText(this, R.string.active_gps, Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(this, R.string.error_404, Toast.LENGTH_SHORT).show()
    }


    private fun startActivityByResponseStatus(res: String) {
        val result = Gson().fromJson(res, Result::class.java)
        val statusCode = result.status

        when (statusCode) {
            rKeys.CODE_200 -> {
                //Toast.makeText(this, rKeys.CODE_200, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PlaceActivity::class.java)
                intent.putExtra("response", res)
                startActivity(intent)
            }
            rKeys.CODE_400 -> {
                Toast.makeText(this, rKeys.CODE_400, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PlacesErrorActivity::class.java)
                intent.putExtra(rKeys.TAG_QUEUE, statusCode)
                startActivity(intent)
            }
            rKeys.CODE_500 -> {
                Toast.makeText(this, rKeys.CODE_500, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PlacesErrorActivity::class.java)
                intent.putExtra(rKeys.TAG_QUEUE, statusCode)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (isLocationEnabled()) {
            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                val location: Location? = task.result
                if (location == null) {
                    requestNewLocationData()
                } else {
                    lat = location.latitude
                    long = location.longitude
                    Toast.makeText(
                        applicationContext, "Lat: ${lat}," +
                                " Lon: ${long}", Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val mLastLocation: Location = locationResult!!.lastLocation
            lat = mLastLocation.latitude
            long = mLastLocation.longitude
            Toast.makeText(
                applicationContext, "Lat: ${lat}, Lon: ${long}", Toast.LENGTH_LONG
            ).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER
                )
    }
}
