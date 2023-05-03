package com.example.hostellisting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1_payment);

        TextView txt = findViewById(R.id.textView15);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changehome();
            }
        });
    }

    private void changehome() {
        Intent i = new Intent(this,MainH.class);
        startActivity(i);
    }
}

