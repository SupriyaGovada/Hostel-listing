package com.example.hostellisting;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginH extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    ConstraintLayout imgCardMobile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hl_login);

        EditText edtMobile = findViewById(R.id.edtMobile);
        EditText edtMobile1 = findViewById(R.id.edtMobile1);

        TextView logsign = findViewById(R.id.txtLogin);
        logsign.setOnClickListener(view -> {

            String email = edtMobile.getText().toString();
            String pass = edtMobile1.getText().toString();

            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(
                            l ->{
                                finishAffinity();
                                startActivity(new Intent(this, MainH.class));
                            }
                    ).addOnFailureListener(
                            vi->{
                                Toast.makeText(this, "Input correct credentials", Toast.LENGTH_SHORT).show();
                            }
                    );
        });

        TextView sign = findViewById(R.id.textView32);
        sign.setOnClickListener(
                a->{
                    startActivity(new Intent(this, Register.class));
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, MainH.class));
        }
    }
}
