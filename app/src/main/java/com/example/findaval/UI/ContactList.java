package com.example.findaval.UI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.findaval.Adapter.ContactAdapter;
import com.example.findaval.Database.ContactArray;
import com.example.findaval.R;
import com.example.findaval.databinding.FragmentContactListBinding;

import java.util.ArrayList;


public class ContactList extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        View.OnClickListener {


//    @SuppressLint("InlinedApi")
//    private final static String[] FROM_COLUMNS = {
//
//                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
//                    ContactsContract.Contacts.DISPLAY_NAME
//    };


    private final static int[] TO_IDS = {
            android.R.id.text1
    };

    RecyclerView recyclerView;
    Uri contactUri;
    String contactKey;
    long contactId;
    ContactAdapter contactAdapter;
    ArrayList<ContactArray>contactArrays;

    ActivityResultLauncher<String>requestPermission;

    FragmentContactListBinding contactListBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        requestPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->{

                    if (isGranted){
                        Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        Toast.makeText(getActivity(), "Permission not granted", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contactListBinding = FragmentContactListBinding.inflate(getLayoutInflater());
        View view = contactListBinding.getRoot();

//        String contactName = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
//        int id = android.R.id.text1;
        recyclerView = contactListBinding.contactRecycler;

      contactArrays = new ArrayList<>();

       contactAdapter = new ContactAdapter(getActivity(),contactArrays);


      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

          if(ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
              getContacts();
              Toast.makeText(getActivity(), "Photo loading ", Toast.LENGTH_SHORT).show();
          } else {
              requestPermission.launch(Manifest.permission.READ_CONTACTS);
              Toast.makeText(getActivity(), "Permission not granted ", Toast.LENGTH_SHORT).show();
          }
      }

        return view;



    }


    public void getContacts(){

        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()){

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            @SuppressLint("Range") int photo = R.id.profile;

            ContactArray contactArray = new ContactArray(name,photo,number);
            contactArrays.add(contactArray);
            contactAdapter.notifyDataSetChanged();

        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(contactAdapter);
    }
    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}