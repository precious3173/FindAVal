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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUPFragment extends Fragment {

    FragmentSignUPBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    FirebaseAuth mAuth;
    FirebaseUser user;



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



                        binding.signup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(!binding.fullname.getText().toString().trim().equals("")) {
                                    if(!binding.email.getText().toString().trim().equals("")){
                                        if (!binding.phoneNumber.getText().toString().trim().equals("")){
                                            if(!binding.password.getText().toString().trim().equals("")){
                                                binding.progressbars.setVisibility(View.VISIBLE);

                                     mAuth.createUserWithEmailAndPassword(binding.email.toString(), binding.password.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                         @Override
                                         public void onComplete(@NonNull Task<AuthResult> task) {

                                             if(task.isSuccessful()){

                                                 user = mAuth.getCurrentUser();
                                                 String id = user.getUid();

                                                DatabaseReference databaseReference= myRef.child(id);

                                                Map<String, String>map = new HashMap<>();
                                                map.put("id", id);
                                                map.put("Email", binding.email.getText().toString());
                                                map.put("Fullname", binding.fullname.getText().toString());
                                                map.put("phoneNumber", binding.phoneNumber.getText().toString());

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
                                         }
                                     });
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
