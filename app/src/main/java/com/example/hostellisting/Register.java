package com.example.hostellisting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Implementing onCreate function to connect layout resource file
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h1_register);


        TextView signup = findViewById(R.id.txtSign);
        EditText name = findViewById(R.id.textView28);
        EditText email = findViewById(R.id.textView26);
        EditText pass = findViewById(R.id.textView29);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();

                Map<String, Object> data = new HashMap<>();
                data.put("Name", Name);
                data.put("Email", Email);
                data.put("Mobile number", "");

                db.collection("Hostel_Users").document(Email)
                        .set(data).addOnCompleteListener(
                                task ->
                                {
                                    change_home(email.getText().toString(), pass.getText().toString());
                                }
                        ).addOnFailureListener(
                                task ->{

                                }
                        );
            }
        });

        TextView t3 = findViewById(R.id.textView33);
        t3.setOnClickListener(t->{
            startActivity(new Intent(this, LoginH.class));
        });

    }
    //Implementing onstart function
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    //Implementing function to navigate to Home screen
    private void change_home(String email,String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( tak -> {
                        finishAffinity();
                        Toast.makeText(this, "Successfully created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainH.class);
                        startActivity(intent);
                });
    }
}

