package com.example.findaval.SigninAndSignUp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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





                        binding.signup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(!binding.fullname.getText().toString().trim().equals("")) {
                                    if(!binding.email.getText().toString().trim().equals("")){
                                        if (!binding.phoneNumber.getText().toString().trim().equals("")){
                                            if(!binding.password.getText().toString().trim().equals("")){
                                                 //binding.progressbars.is;

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

                                                        loginDetails.put("fullname", binding.fullname.toString());
                                                        loginDetails.put("email", binding.email.toString());
                                                        loginDetails.put("password", binding.password.toString());
                                                        loginDetails.put("phonenumber", binding.phoneNumber.toString());

                                                        return loginDetails;
                                                    }
                                                };

                                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                                requestQueue.add(stringRequest);
                                            }


                                    }
                                        }
                                    } else {
                                    Toast.makeText(getContext(), "Field is empty", Toast.LENGTH_SHORT).show();

                                }
                                }
                        });



//
    }
}
