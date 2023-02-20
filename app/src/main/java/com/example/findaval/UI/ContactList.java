package com.example.findaval.UI;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findaval.Adapter.ContactAdapter;
import com.example.findaval.R;
import com.example.findaval.databinding.FragmentContactListBinding;


public class ContactList extends Fragment {


    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME
    };

    private final static int[] TO_IDS = {
            android.R.id.text1
    };

    RecyclerView recyclerView;
    Uri contactUri;
    String contactKey;
    long contactId;
    ContactAdapter contactAdapter;


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

        recyclerView = contactListBinding.contactRecycler;



        return view;




    }
}