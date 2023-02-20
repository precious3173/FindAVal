package com.example.findaval.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findaval.Database.ContactArray;
import com.example.findaval.databinding.ContactResourceBinding;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
List<ContactArray>contactArray;
Context context;


 public ContactAdapter(Context context, List<ContactArray> contactArray){
     this.context = context;
     this.contactArray = contactArray;


 }

 public class ContactViewHolder extends RecyclerView.ViewHolder {
     public ContactResourceBinding contactResourceBinding;
//     public ContactViewHolder(@NonNull View itemView) {
//         super(itemView);
//     }
     public ContactViewHolder(@NonNull ContactResourceBinding contactResourceBinding){
         super(contactResourceBinding.getRoot());

       this.contactResourceBinding = contactResourceBinding;
     }

     void bindview(ContactArray contactArray){

         contactResourceBinding.nameText.setText(contactArray.getNameText());
         contactResourceBinding.status.setText(contactArray.getStatus());
         contactResourceBinding.profile.setImageResource(contactArray.getProfile());

     }
 }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     ContactResourceBinding contactResourceBinding = ContactResourceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
     return new ContactViewHolder(contactResourceBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
     holder.bindview(contactArray.get(position));

    }

    @Override
    public int getItemCount() {
        return contactArray.size();
    }


}
