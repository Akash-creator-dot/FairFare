package com.example.fairfare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class RideDetailsActivity extends AppCompatActivity {

    private TextView rideName, rideDescription;
    private LottieAnimationView rideImage;

    private double pickupLatitude, pickupLongitude, destinationLatitude, destinationLongitude;
    private String selectedRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);

        rideName = findViewById(R.id.ride_name);
        rideDescription = findViewById(R.id.ride_description);
        rideImage = findViewById(R.id.ride_image);
        ImageButton backButton = findViewById(R.id.back_button);
        Button compareButton = findViewById(R.id.book);  // Rename it if needed

        // ✅ Receive ride info
        Intent intent = getIntent();
        selectedRide = intent.getStringExtra("ride_name");
        String rideDesc = intent.getStringExtra("ride_description");

        rideName.setText(selectedRide);
        rideDescription.setText(rideDesc);
        if (selectedRide != null) {
            if (selectedRide.equals("Sedan")) {
                rideImage.setAnimation(R.raw.sedan);
            } else if (selectedRide.equals("SUV")) {
                rideImage.setAnimation(R.raw.suv);
            } else if (selectedRide.equals("Auto")) {
                rideImage.setAnimation(R.raw.auto);
            } else if (selectedRide.equals("Bike")) {
                rideImage.setAnimation(R.raw.bike);
            }

            // ✅ Receive coordinates from intent
            pickupLatitude = intent.getDoubleExtra("pickupLatitude", 0.0);
            pickupLongitude = intent.getDoubleExtra("pickupLongitude", 0.0);
            destinationLatitude = intent.getDoubleExtra("destinationLatitude", 0.0);
            destinationLongitude = intent.getDoubleExtra("destinationLongitude", 0.0);

            // ✅ Handle back
            backButton.setOnClickListener(v -> finish());

            // ✅ On "Compare" click, send to ComparisonActivity
            compareButton.setOnClickListener(v -> {
                Intent compareIntent = new Intent(RideDetailsActivity.this, Comparision.class);
                compareIntent.putExtra("pickupLatitude", pickupLatitude);
                compareIntent.putExtra("pickupLongitude", pickupLongitude);
                compareIntent.putExtra("destinationLatitude", destinationLatitude);
                compareIntent.putExtra("destinationLongitude", destinationLongitude);
                compareIntent.putExtra("rideMode", selectedRide); // e.g., "Sedan"
                startActivity(compareIntent);
            });
        }
    }
}
