package com.example.fairfare;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Random;

public class Comparision extends AppCompatActivity {

    CardView olacard, ubercard, rapidocard;
    TextView uberprice,ubertime,olaprice,olatime,rapidoprice,rapidotime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparision);

        // Get pickup/drop location from intent
        double pickupLat = getIntent().getDoubleExtra("pickupLatitude", 0);
        double pickupLng = getIntent().getDoubleExtra("pickupLongitude", 0);
        double dropLat = getIntent().getDoubleExtra("destinationLatitude", 0);
        double dropLng = getIntent().getDoubleExtra("destinationLongitude", 0);
        String rideName = getIntent().getStringExtra("ride_name");
        String rideDescription = getIntent().getStringExtra("ride_description");
        // Find cards
        olacard = findViewById(R.id.olacard);
        ubercard = findViewById(R.id.ubercard);
        rapidocard = findViewById(R.id.rapidocard);
        //find textviews
        uberprice=findViewById(R.id.uberprice);
        ubertime=findViewById(R.id.ubertime);
        olaprice=findViewById(R.id.olaprice);
        olatime=findViewById(R.id.olatime);
        rapidoprice=findViewById(R.id.rapidoprice);
        rapidotime=findViewById(R.id.rapidotime);
        // Uber Card Click
        Random random = new Random();
        int uberPriceVal = 100 + random.nextInt(101);
        int uberTimeVal = 3 + random.nextInt(15);

        int olaPriceVal = 100 + random.nextInt(101);
        int olaTimeVal = 3 + random.nextInt(5);

        int rapidoPriceVal = 100 + random.nextInt(101);
        int rapidoTimeVal = 3 + random.nextInt(5);

// Set values to TextViews
        uberprice.setText("₹" + uberPriceVal);
        ubertime.setText(uberTimeVal + " min");

        olaprice.setText("₹" + olaPriceVal);
        olatime.setText(olaTimeVal + " min");

        rapidoprice.setText("₹" + rapidoPriceVal);
        rapidotime.setText(rapidoTimeVal + " min");

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
