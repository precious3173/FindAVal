package com.example.findaval.SigninAndSignUp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.findaval.databinding.FragmentSignUPBinding;

import java.util.HashMap;
import java.util.Map;


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

      if(fullName.getText().toString().equals("") && phoneNumber.getText().toString().equals("")
      && password.getText().toString().equals("") && email.getText().toString().equals("")){

      binding.signup.setBackgroundColor(R.color.grey);
      }
      else {


          binding.signup.isEnabled();

          binding.signup.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  progressBar.isShown();

                  StringRequest stringRequest = new StringRequest(Request.Method.POST,
                          Constant.URL, new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {

                          if(response.equals("successful")){
                              Intent intent = new Intent(getActivity(), MainActivity.class);
                              startActivity(intent);

                          }
                          else {

                              Toast.makeText(getContext(), "Registration was unsuccessful", Toast.LENGTH_SHORT).show();
                          }
                      }
                  }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                          error.printStackTrace();
                      }
                  }){
                      @Override
                      protected Map<String, String> getParams() throws AuthFailureError{

                          Map<String, String>loginDetails = new HashMap<>();

                          loginDetails.put("fullname", fullName.toString());
                          loginDetails.put("email", email.toString());
                          loginDetails.put("password", password.toString());
                          loginDetails.put("phonenumber", phoneNumber.toString());

                          return loginDetails;
                      }
                  };

                  RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                  requestQueue.add(stringRequest);
              }
          });
      }
        return binding.getRoot();
    }
}