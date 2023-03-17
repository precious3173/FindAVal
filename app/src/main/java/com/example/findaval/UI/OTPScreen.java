package com.example.findaval.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.findaval.R;
import com.example.findaval.databinding.ActivityOtpscreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OTPScreen extends AppCompatActivity {

    String verificationId;
    PhoneAuthProvider.ForceResendingToken token;
    ActivityOtpscreenBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();

            verificationId = getIntent().getStringExtra("verificationId").toString();
            token= getIntent().getParcelableExtra("token");


            binding.signin.setEnabled(true);

            binding.signin.setEnabled(false);
            binding.OTP.setText("null");
            Toast.makeText(getApplicationContext(), verificationId, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), verificationId, Toast.LENGTH_SHORT).show();



        binding.OTP.setText(verificationId);
        binding.OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, getCode);

                signinWithCredential(credential);
            }
        });


    }

    private void signinWithCredential(PhoneAuthCredential credential) {

        auth.signInWithCredential(credential).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(OTPScreen.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}