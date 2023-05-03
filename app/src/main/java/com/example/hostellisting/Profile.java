package com.example.hostellisting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Profile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hl_profile);

        TextView btn = findViewById(R.id.editprof);
        btn.setOnClickListener(view -> {
            change_profile();
        });

    }

    private void change_profile() {
        Intent intent = new Intent(this, Editprofile.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView username = findViewById(R.id.name);
        TextView useremail = findViewById(R.id.user_email);
        TextView usernum = findViewById(R.id.user_num);

        String user_email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
//        assert user_email != null;
        db.collection("Hostel_Users").document(user_email)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String Name = document.getString("Name");
                            username.setText(Name);
                            String Email = document.getString("Email");
                            useremail.setText(Email);
                            String Address = document.getString("Mobile Number");
                            usernum.setText(Address);
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.getException());
                    }
                });

    }

}
