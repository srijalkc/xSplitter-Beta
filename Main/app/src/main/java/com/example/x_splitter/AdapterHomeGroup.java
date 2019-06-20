package com.example.x_splitter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterHomeGroup extends RecyclerView.Adapter<AdapterHomeGroup.MyViewHolder> {

    ArrayList<ModelHomeGroup> Data = new ArrayList<>();

    public AdapterHomeGroup(ArrayList<ModelHomeGroup> data){
        Data = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView GroupName;
        CircleImageView GroupImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            GroupName = itemView.findViewById(R.id.group_name);
            GroupImage = itemView.findViewById(R.id.group_image);
        }
    }
    @NonNull
    @Override
    public AdapterHomeGroup.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_group_item,parent,false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeGroup.MyViewHolder holder, int position) {

        holder.GroupName.setText(Data.get(position).getGroupName()); //getter made in ModelHomeGroup
        holder.GroupImage.setImageResource(Data.get(position).getGroupImage());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
}
