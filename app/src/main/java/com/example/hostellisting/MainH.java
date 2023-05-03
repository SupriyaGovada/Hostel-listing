package com.example.hostellisting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainH extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public DocumentSnapshot data;

    public TextView loc;
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // Convert location into address
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                // Show address in TextView or other UI component
                loc.setText(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hl_main);

        TextView txt = findViewById(R.id.textView4);
        txt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt.getText().toString();
                txt.setTextColor(R.color.purple_200);
                changeh1();
            }
        });
        TextView txt1 = findViewById(R.id.textView7);
        txt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt1.getText().toString();
                txt1.setTextColor(R.color.purple_200);
                changeh2();
            }
        });

    ImageView img = findViewById(R.id.imgUser);
        img.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeprofile();
        }
    });
        ImageView imglog = findViewById(R.id.imgLogout);
        imglog.setOnClickListener(view -> {
            finishAffinity();
            changelogin();
        });
        ImageView img1 = findViewById(R.id.imageView19);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changedraw();
            }
        });
}

    private void changeh2() {
        Intent i4 = new Intent(this,Hostel2.class);
        startActivity(i4);
    }

    private void changeh1() {
        Intent i = new Intent(this,Hostel1.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loc = findViewById(R.id.textView);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            Toast.makeText(this, "Please enable the location permision!", Toast.LENGTH_SHORT).show();
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // Request location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }


    private void changelogin() {
        Intent i3 = new Intent(this,LoginH.class);
        startActivity(i3);
    }

    private void changedraw() {
        Intent i2 = new Intent(this,Drawable.class);
        startActivity(i2);
    }

    private void changeprofile() {
        Intent i1 = new Intent(this,Profile.class);
        startActivity(i1);
    }

}
