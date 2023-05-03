package com.example.hostellisting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Editprofile extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public String mCurrentPhotoPath;
    final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hl_editprofile);
        TextView profile = findViewById(R.id.textView17);
        EditText name = findViewById(R.id.editText2);
        EditText email = findViewById(R.id.editText);
        EditText mob = findViewById(R.id.editText3);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Mobile_Number = mob.getText().toString();
                Map<String, Object> data = new HashMap<>();
                data.put("Name", Name);
                data.put("Email", Email);
                data.put("Mobile Number", Mobile_Number);
                String curr_email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
                db.collection("Hostel_Users").document(curr_email)
                        .set(data).addOnCompleteListener(
                                task ->
                                {
                                    finishAffinity();
                                    change_home();
                                }
                        ).addOnFailureListener(
                                task ->{
                                }
                        );
            }
        });
        ImageView img = findViewById(R.id.imageView10);
        img.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create a file to save the image
                File photoFile = null;
                try {
                    photoFile = createImageFile();
//                    profile_img.setImageURI(Uri.parse(mCurrentPhotoPath));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Display the captured image
            ImageView profile_img = findViewById(R.id.imageView10);
            profile_img.setImageURI(Uri.parse(mCurrentPhotoPath));
        }
    }

    private void change_home() {
        Intent intent = new Intent(this, MainH.class);
        startActivity(intent);
    }

}


