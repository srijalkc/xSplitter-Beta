package com.example.x_splitter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMember extends RecyclerView.Adapter<AdapterMember.MemberViewHolder> {

    private Context context;
    private ArrayList<ModelMember> data = new ArrayList<>();

    public AdapterMember(Context context, ArrayList<ModelMember> data) {
        this.context = context;
        this.data = data;
    }


    public class MemberViewHolder extends RecyclerView.ViewHolder{
        CircleImageView inmember_image;
        TextView inmember_name;

        public MemberViewHolder (View itemView){
            super(itemView);
            inmember_image = itemView.findViewById(R.id.member_image);
            inmember_name = itemView.findViewById(R.id.member_name);
        }
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_each_members,parent,false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.inmember_image.setImageResource(data.get(position).getMember_image());
        holder.inmember_name.setText(data.get(position).getMember_name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
