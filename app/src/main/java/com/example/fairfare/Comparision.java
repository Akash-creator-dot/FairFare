package com.example.fairfare;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Comparision extends AppCompatActivity {

    CardView olacard, ubercard, rapidocard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparision);

        // Get pickup/drop location from intent
        double pickupLat = getIntent().getDoubleExtra("pickupLatitude", 0);
        double pickupLng = getIntent().getDoubleExtra("pickupLongitude", 0);
        double dropLat = getIntent().getDoubleExtra("destinationLatitude", 0);
        double dropLng = getIntent().getDoubleExtra("destinationLongitude", 0);

        // Find cards
        olacard = findViewById(R.id.olacard);
        ubercard = findViewById(R.id.ubercard);
        rapidocard = findViewById(R.id.rapidocard);

        // Uber Card Click
        ubercard.setOnClickListener(v -> {
            String uri = "uber://?action=setPickup" +
                    "&pickup[latitude]=" + pickupLat +
                    "&pickup[longitude]=" + pickupLng +
                    "&dropoff[latitude]=" + dropLat +
                    "&dropoff[longitude]=" + dropLng +
                    "&dropoff[nickname]=Drop";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            intent.setPackage("com.ubercab");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Uber app not installed
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.ubercab")));
            }
        });

        // Ola Card Click
        olacard.setOnClickListener(v -> {
            String uri = "olacabs://app/launch?lat=" + pickupLat +
                    "&lng=" + pickupLng +
                    "&drop_lat=" + dropLat +
                    "&drop_lng=" + dropLng;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            intent.setPackage("com.olacabs.customer");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Ola app not installed
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.olacabs.customer")));
            }
        });

        // Rapido Card Click
        rapidocard.setOnClickListener(v -> {
            String uri = "rapido://?action=setPickup" +
                    "&pickup[latitude]=" + pickupLat +
                    "&pickup[longitude]=" + pickupLng +
                    "&dropoff[latitude]=" + dropLat +
                    "&dropoff[longitude]=" + dropLng +
                    "&dropoff[nickname]=Drop";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            intent.setPackage("com.rapido.passenger");

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Uber app not installed
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.rapido.passenger")));
            }
        });
    }
}
