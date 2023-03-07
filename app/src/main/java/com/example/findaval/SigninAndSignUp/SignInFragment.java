package com.example.findaval.SigninAndSignUp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Database;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.findaval.R;
import com.example.findaval.UI.MainActivity;
import com.example.findaval.databinding.FragmentSignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignInFragment extends Fragment {

    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FragmentSignInBinding signInBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        signInBinding = FragmentSignInBinding.inflate(getLayoutInflater());
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        signInBinding.email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                checkField();
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signInBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                checkField();
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        signin();

        return signInBinding.getRoot();
    }

    private void checkField() {
 if (!signInBinding.email.getText().toString().equals("")){
     if(!signInBinding.password.getText().toString().equals("")){

         signInBinding.signin.setEnabled(true);
     }}
   else {
       signInBinding.signin.setEnabled(false);
 }

    }


    private void signin() {
        signInBinding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInBinding.progressbar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(signInBinding.email.getText().toString(), signInBinding.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            signInBinding.progressbar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(getContext(), "Unable to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}