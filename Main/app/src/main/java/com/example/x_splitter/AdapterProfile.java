package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.ProfileHolder>  {
        ArrayList<ModelProfile> Data = new ArrayList<>();
        Context context;

    public AdapterProfile(Context context,ArrayList<ModelProfile> data) {
        Data = data;
        this.context = context;
    }

    public class ProfileHolder extends RecyclerView.ViewHolder {
        TextView friendName;
        TextView friendEmail;

        public ProfileHolder(View itemView) {
            super(itemView);
            friendName = itemView.findViewById(R.id.frn_uname);
            friendEmail = itemView.findViewById(R.id.frn_email);
        }
    }

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_my_fren,parent,false);
        return new AdapterProfile.ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        holder.friendName.setText(Data.get(position).getFriendName());
        holder.friendEmail.setText(Data.get(position).getFriendEmail());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
}
