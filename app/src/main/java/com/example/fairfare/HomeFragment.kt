package com.example.fairfare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.button.MaterialButton
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var pickupLocation: EditText
    private lateinit var destination: EditText
    private lateinit var findFaresButton: MaterialButton
    private lateinit var googleMap: GoogleMap

    // Hold coordinates here
    private var pickupLatLng: LatLng? = null
    private var destinationLatLng: LatLng? = null

    companion object {
        private const val AUTOCOMPLETE_PICKUP_REQUEST = 1
        private const val AUTOCOMPLETE_DESTINATION_REQUEST = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), "AIzaSyB29I5knYAO6Sb-AWY06mRygi8VVutduB8")
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        pickupLocation = view.findViewById(R.id.pickup_location)
        destination = view.findViewById(R.id.destination)
        findFaresButton = view.findViewById(R.id.find_fares_button)

        pickupLocation.setOnClickListener {
            launchAutocomplete(AUTOCOMPLETE_PICKUP_REQUEST)
        }

        destination.setOnClickListener {
            launchAutocomplete(AUTOCOMPLETE_DESTINATION_REQUEST)
        }
        var pickuplocations=pickupLocation.text.toString();
        var destinations=destination.text.toString();
        findFaresButton.setOnClickListener {
            if (pickupLatLng == null || destinationLatLng == null) {
                Toast.makeText(requireContext(), "Please select both locations", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(requireContext(), SelectMode::class.java).apply {
                    putExtra("pickupLatitude", pickupLatLng!!.latitude)
                    putExtra("pickupLongitude", pickupLatLng!!.longitude)
                    putExtra("destinationLatitude", destinationLatLng!!.latitude)
                    putExtra("destinationLongitude", destinationLatLng!!.longitude)
                }
                intent.putExtra("pickup", pickuplocations);
                intent.putExtra("destination", destinations);
                startActivity(intent)
            }
        }

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = false
        googleMap.uiSettings.isMyLocationButtonEnabled = true

        val delhiLatLng = LatLng(28.6139, 77.2090)
        val cameraPosition = CameraPosition.Builder()
            .target(delhiLatLng)
            .zoom(12f)
            .build()

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun launchAutocomplete(requestCode: Int) {
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .setCountry("IN")
            .build(requireContext())

        startActivityForResult(intent, requestCode)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            val place = Autocomplete.getPlaceFromIntent(data)
            when (requestCode) {
                AUTOCOMPLETE_PICKUP_REQUEST -> {
                    pickupLocation.setText(place.name)
                    pickupLatLng = place.latLng
                }
                AUTOCOMPLETE_DESTINATION_REQUEST -> {
                    destination.setText(place.name)
                    destinationLatLng = place.latLng
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(requireContext(), "Location selection canceled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Error retrieving location", Toast.LENGTH_SHORT).show()
        }
    }
}
