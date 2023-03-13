package com.example.findaval.SigninAndSignUp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findaval.Constant;
import com.example.findaval.R;
import com.example.findaval.UI.MainActivity;
import com.example.findaval.UI.OTPScreen;
import com.example.findaval.databinding.FragmentSignUPBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class SignUPFragment extends Fragment {

    FragmentSignUPBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    FirebaseAuth mAuth;
    FirebaseUser user;
    String countryCode;
    String VerificationId;
    String number;
    CountryCodePicker countryCodePicker;
    PhoneAuthProvider.ForceResendingToken mResendToken;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUPBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


          countryCodePicker = binding.countryPicker;
          countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
              @Override
              public void onCountrySelected() {
                 countryCode = countryCodePicker.getSelectedCountryCodeWithPlus();

              }
          });



          countryCodePicker.registerCarrierNumberEditText(binding.phoneNumber);

                        binding.signup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                if(!binding.fullname.getText().toString().trim().equals("")) {
                                    if(!binding.email.getText().toString().trim().equals("")){
                                        if (!binding.phoneNumber.getText().toString().trim().equals("")){
                                            if(!binding.password.getText().toString().trim().equals("")){
                                                binding.progressbars.setVisibility(View.VISIBLE);
                                                number = countryCode+binding.phoneNumber.getText().toString();
                                                Toast.makeText(getContext(), number, Toast.LENGTH_SHORT).show();
                                                binding.signup.setEnabled(false);

                                                PhoneAuthProvider.OnVerificationStateChangedCallbacks callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                                                    @Override
                                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                                        user = mAuth.getCurrentUser();
                                                        String id = user.getUid();

                                                        DatabaseReference databaseReference= myRef.child(id);

                                                        Map<String, String>map = new HashMap<>();
                                                        map.put("id", id);
                                                        map.put("Email", binding.email.getText().toString());
                                                        map.put("Fullname", binding.fullname.getText().toString());
                                                        map.put("phoneNumber", number);

                                                        databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful()){
                                                                    binding.progressbars.setVisibility(View.GONE);
                                                                    Toast.makeText(getContext(), "Registration was successful", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else{
                                                                    binding.progressbars.setVisibility(View.GONE);
                                                                    Toast.makeText(getContext(), "Registration not sueccessful", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });



                                                    }

                                                    @Override
                                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                                        Log.w(TAG, "onVerificationFailed", e);

                                                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                                            e.printStackTrace();
                                                            // Invalid request
                                                        } else if (e instanceof FirebaseTooManyRequestsException) {
                                                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                                            e.printStackTrace();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCodeSent(@NonNull String mVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                                        VerificationId = mVerificationId;
                                                        mResendToken = token;
                                                          activateOTP(VerificationId);
                                                    }
                                                };



                                                PhoneAuthOptions authOptions = PhoneAuthOptions.newBuilder(
                                                        mAuth
                                                ).setPhoneNumber(number).setTimeout(60L, TimeUnit.SECONDS).
                                                        setActivity(getActivity()).setCallbacks(callback).build();

                                                PhoneAuthProvider.verifyPhoneNumber(authOptions);
                                             //   PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, String.valueOf(mResendToken));

                                            }}}
                                    } else {
                                    Toast.makeText(getContext(), "Field is empty", Toast.LENGTH_SHORT).show();

                                }
                                }
                        });




    }

    private void activateOTP(String mVerificationId) {

        Intent intent = new Intent(getActivity(), OTPScreen.class);
        intent.putExtra("verificationId", mVerificationId);
        Toast.makeText(getActivity(),mVerificationId, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        binding.progressbars.setVisibility(View.GONE);

    }


}
