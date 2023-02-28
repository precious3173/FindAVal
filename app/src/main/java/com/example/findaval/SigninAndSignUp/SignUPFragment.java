package com.example.findaval.SigninAndSignUp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.findaval.R;
import com.example.findaval.databinding.FragmentSignUPBinding;


public class SignUPFragment extends Fragment {

    FragmentSignUPBinding binding;
    EditText fullName, phoneNumber, password, email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUPBinding.inflate(getLayoutInflater());



      ProgressBar progressBar =  binding.progressbars;
      fullName = binding.fullname;
      phoneNumber = binding.phoneNumber;
      password = binding.password;
      email = binding.email;

      if(fullName.getText().toString().isEmpty() && phoneNumber.getText().toString().isEmpty()
      && password.getText().toString().isEmpty() && email.getText().toString().isEmpty()){

      binding.signup.setBackgroundColor(R.color.grey);
      }
      else {

          binding.signup.isEnabled();
      }
        return binding.getRoot();
    }
}