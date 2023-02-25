package com.example.findaval.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findaval.Database.ChatArray;
import com.example.findaval.Database.ContactArray;
import com.example.findaval.databinding.ChatResourceBinding;

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
        public ChatResourceBinding chatResourceBinding;

        public ChatHolder(@NonNull ChatResourceBinding chatResourceBinding) {
            super(chatResourceBinding.getRoot());

            this.chatResourceBinding = chatResourceBinding;
        }
        void bindview(ChatArray chatArray){

            chatResourceBinding.nameText.setText(chatArray.getName());
            chatResourceBinding.chat.setText(chatArray.getChat());
            chatResourceBinding.profile.setImageResource(chatArray.getProfile());
        }
    }
}
