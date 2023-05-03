package com.example.hostellisting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hostel1 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hl_booked);

        TextView txt = findViewById(R.id.textView34);
        txt.setOnClickListener(view -> changepay());


        TextView paylater = findViewById(R.id.textView33);
        paylater.setOnClickListener(view -> {
            String x = paylater.getText().toString();
            paylater.setTextColor(R.color.purple_200);
        });

    }

    private void changepay() {
        Intent i = new Intent(this, Payment.class);
        startActivity(i);
    }
}

