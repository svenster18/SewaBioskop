package com.aufairfani.sewabioskop

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.aufairfani.sewabioskop.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var movie: ItemsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pilih Bioskop"

        movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_MOVIE, ItemsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MOVIE)
        }

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
            val mFragmentManager = supportFragmentManager
            val mReservationFragment = ReservationFragment()
            val fragment = mFragmentManager.findFragmentByTag(ReservationFragment::class.java.simpleName)

            if (movie != null) {
                val mBundle = Bundle()
                mBundle.putString(ReservationFragment.EXTRA_IMAGE, movie!!.image)
                mBundle.putString(ReservationFragment.EXTRA_TITLE, movie!!.title)
                mBundle.putString(ReservationFragment.EXTRA_GENRES, movie!!.genres)
                mBundle.putString(ReservationFragment.EXTRA_CINEMA, marker.title)

                mReservationFragment.arguments = mBundle

                if (fragment !is ReservationFragment) {
                    Log.d("MapsActivity", "Fragment Name :" + ReservationFragment::class.java.simpleName)
                    mFragmentManager
                        .beginTransaction()
                        .replace(R.id.map, mReservationFragment, ReservationFragment::class.java.simpleName)
                        .commit()
                }
            }
            true
        }
    }
}