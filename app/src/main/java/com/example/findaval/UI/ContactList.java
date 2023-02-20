package com.example.findaval.UI;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findaval.R;
import com.example.findaval.databinding.FragmentContactListBinding;


public class ContactList extends Fragment {

    FragmentContactListBinding contactListBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contactListBinding = FragmentContactListBinding.inflate(getLayoutInflater());
        View view = contactListBinding.getRoot();
        return view;


    }
}