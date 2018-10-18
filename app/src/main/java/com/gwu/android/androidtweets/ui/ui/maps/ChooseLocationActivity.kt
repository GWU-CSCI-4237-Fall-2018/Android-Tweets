package com.gwu.android.androidtweets.ui.ui.maps

import android.content.Intent
import android.location.Address
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gwu.android.androidtweets.R
import com.gwu.android.androidtweets.data.location.ReverseGeocodingTask
import com.gwu.android.androidtweets.ui.tweets.TweetsActivity

class ChooseLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    // companion objects provide "static" access, in Java terms...
    companion object {
        // ... because we want this constant to be shared with another class
        val INTENT_KEY_USERNAME = "USERNAME"
    }

    private lateinit var map: GoogleMap

    private lateinit var confirm: Button

    private lateinit var userLocation: ImageButton

    private lateinit var progress: ProgressBar

    private var currentAddress: Address? = null

    /**
     * Called when the Activity is being rendered for the first time, but before anything is
     * shown to the user.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_location)

        // Retrieve the username out of the intent, use it to set the title
        val username = intent.getStringExtra(INTENT_KEY_USERNAME)
        title = getString(R.string.welcome_name, username)

        confirm = findViewById(R.id.confirm)
        userLocation = findViewById(R.id.current_location)
        progress = findViewById(R.id.progress)

        // Gets a reference to the Google Maps fragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        // Load the actual map, which completes at some point in the future. You
        // supply a callback (OnMapReadyCallback) to be notified when it completes.
        mapFragment.getMapAsync(this)

        confirm.setOnClickListener {
            // ?.let is like a Kotlin shorthand for: if (currentAddress != null)
            currentAddress?.let { address ->
                // Launch the TweetsActivity, passing it the Address the user chose
                val intent = Intent(this, TweetsActivity::class.java)
                intent.putExtra(TweetsActivity.INTENT_KEY_LOCATION, address)
                startActivity(intent)
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * <p>
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setOnMapLongClickListener { latLng: LatLng ->
            // Create our AsyncTask to do reverse geocoding, passing it callbacks which execute
            // depending on the geocoding result
            val reverseGeocodingTask =
                ReverseGeocodingTask(
                    context = this,
                    onSuccessListener = { address -> handleGeocodingSuccess(address) },
                    onErrorListener = { handleGeocodingError() }
                )

            // Start the task, passing it the LatLng where the user clicked
            progress.visibility = View.VISIBLE
            reverseGeocodingTask.execute(latLng)
        }
    }

    /**
     * Geocoding succeeded, so we'll place a marker on the map with the location name and
     * update the confirmation button.
     */
    private fun handleGeocodingSuccess(address: Address) {
        progress.visibility = View.GONE

        val latLng = LatLng(address.latitude, address.longitude)

        // A when statement is similar to a switch. In this case, we use it to set an appropriate
        // title for the Marker.
        val title: String = when {
            address.countryName != null -> address.countryName
            address.adminArea != null -> address.adminArea
            address.locality != null -> address.locality
            else -> "Your Marker"
        }

        currentAddress = address

        // Add the marker to the map & shift the camera
        map.clear()
        map.addMarker(
            MarkerOptions().position(latLng).title(title)
        )
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5.0f))

        updateConfirmButton(address)
    }

    /**
     * Geocoding failed -- display a [Toast] to the user.
     */
    private fun handleGeocodingError() {
        progress.visibility = View.GONE

        Toast.makeText(
            this,
            getString(R.string.no_address_found),
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * Flip the confirmation button to green & display the address as the text.
     */
    private fun updateConfirmButton(address: Address) {
        // Grab a color & drawable from the res folder.
        val greenColor = ContextCompat.getColor(this,
            R.color.button_green
        )
        val checkIcon = ContextCompat.getDrawable(this,
            R.drawable.ic_check_white
        )


        confirm.setBackgroundColor(greenColor)
        confirm.setCompoundDrawablesWithIntrinsicBounds(checkIcon, null, null, null)
        confirm.text = address.getAddressLine(0)
        confirm.isEnabled = true
    }
}
