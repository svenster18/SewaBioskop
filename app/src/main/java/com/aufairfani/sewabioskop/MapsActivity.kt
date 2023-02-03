package com.aufairfani.sewabioskop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.aufairfani.sewabioskop.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pilih Bioskop"

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val cgpAlpha = LatLng(-6.193924061113853, 106.78813220277623)
        val cgpBeta = LatLng(-6.20175020412279, 106.78223868546155)
        val midPoint = LatLng((cgpAlpha.latitude + cgpBeta.latitude)/2, (cgpAlpha.longitude + cgpBeta.longitude)/2)
        mMap.addMarker(MarkerOptions().position(cgpAlpha).title("CGP Alpha"))
        mMap.addMarker(MarkerOptions().position(cgpBeta).title("CGP Beta"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midPoint, 14f))

        mMap.setOnMarkerClickListener {marker ->
            Toast.makeText(this@MapsActivity, marker.title, Toast.LENGTH_SHORT).show()
            true
        }
    }
}