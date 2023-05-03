package com.example.hostellisting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class Drawable extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable);

        TextView name = findViewById(R.id.textView19);
        TextView email = findViewById(R.id.textView20);
        String Email = mAuth.getCurrentUser().getEmail();

        db.collection("Hostel_Users").document(Email)
                .get().addOnCompleteListener(
                        tk->{
                            if (tk.isSuccessful()) {
                                DocumentSnapshot document = tk.getResult();
                                if (document.exists()) {
                                    String Name = document.getString("Name");
                                    name.setText(Name);
                                    String mail = document.getString("Email");
                                    email.setText(Email);
                                }
                            }

                        }
                );

        TextView txt = findViewById(R.id.textView25);
        txt.setOnClickListener(view -> {
           mAuth.signOut();
           finishAffinity();
           startActivity(new Intent(this, LoginH.class));
        });


    }
}

