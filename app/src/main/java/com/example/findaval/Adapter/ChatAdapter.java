package com.example.findaval.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findaval.Database.ContactArray;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    Context context;
    List<ContactArray>contactArrays;

    public ChatAdapter(Context context, List<ContactArray> contactArrays) {
        this.context = context;
        this.contactArrays = contactArrays;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        public ChatHolder(@NonNull View itemView) {
            super();
        }
    }
}
