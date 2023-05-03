package com.example.hostellisting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OtpH extends BaseH{
    EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hl_otp);

        TextView otp =findViewById(R.id.txtverifyotp);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changehome();
            }
        });

        otpDigit1 = findViewById(R.id.otp1);
        otpDigit2 = findViewById(R.id.otp2);
        otpDigit3 = findViewById(R.id.otp3);
        otpDigit4 = findViewById(R.id.otp4);

        editTexts = new EditText[]{otpDigit1, otpDigit2, otpDigit3, otpDigit4};

        otpDigit1.addTextChangedListener(new PinTextWatcher(0));
        otpDigit2.addTextChangedListener(new PinTextWatcher(1));
        otpDigit3.addTextChangedListener(new PinTextWatcher(2));
        otpDigit4.addTextChangedListener(new PinTextWatcher(3));

        otpDigit1.setOnKeyListener(new PinOnKeyListener(0));
        otpDigit2.setOnKeyListener(new PinOnKeyListener(1));
        otpDigit3.setOnKeyListener(new PinOnKeyListener(2));
        otpDigit4.setOnKeyListener(new PinOnKeyListener(3));
    }

    private void changehome() {
        Intent i = new Intent(this,MainH.class);
        startActivity(i);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.txtverifyotp:
                if ((TextUtils.isEmpty(otpDigit1.getText().toString().trim())) || (TextUtils.isEmpty(otpDigit2.getText().toString().trim())) ||
                        (TextUtils.isEmpty(otpDigit3.getText().toString().trim())) || (TextUtils.isEmpty(otpDigit4.getText().toString().trim()))) {
                    toast("Please enter valid otp");
                } else {
                    String otp = otpDigit1.getText().toString() + otpDigit2.getText().toString() + otpDigit3.getText().toString() + otpDigit4.getText().toString();
                    if(otp.equals("1234")){
                        Intent intent = new Intent(this, MainH.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        toast("Invalid otp");
                    }
                }
                break;
            default:
                break;
        }
    }
}
