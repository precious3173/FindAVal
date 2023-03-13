package com.example.findaval.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.findaval.R;
import com.example.findaval.databinding.ActivityOtpscreenBinding;

public class OTPScreen extends AppCompatActivity {

    String verificationId;
    ActivityOtpscreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String OTPCode = binding.OTP.getText().toString();


        Bundle extras = getIntent().getExtras();

        if(extras != null){
            verificationId = extras.getString("verificationId");

            if(verificationId == OTPCode){

                Toast.makeText(this, "Registration is successful", Toast.LENGTH_SHORT).show();
            }
        }


    }
}