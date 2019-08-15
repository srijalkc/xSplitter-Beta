package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterGroup extends RecyclerView.Adapter<AdapterGroup.GroupMyViewHolder> {

    private Context context;
    private ArrayList<ModelGroup> data = new ArrayList<>();

    public AdapterGroup(Context context, ArrayList<ModelGroup> data) {

        this.context = context;
        this.data = data;
    }

    public class GroupMyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView inGroup_image;
        TextView inGroup_name;
        TextView inUnsettle_no;
        TextView inSettle_no;

        public GroupMyViewHolder(View itemView) {
            super(itemView);
            inGroup_image = itemView.findViewById(R.id.group_image);
            inGroup_name = itemView.findViewById(R.id.group_name);
            inUnsettle_no = itemView.findViewById(R.id.g_unsettle_no);
            inSettle_no = itemView.findViewById(R.id.g_settle_no);
        }
    }

    @NonNull
    @Override
    public GroupMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_each_group,parent,false);
        return new GroupMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMyViewHolder holder, int position) {
        holder.inGroup_image.setImageResource(data.get(position).getInGroup_image());
        holder.inGroup_name.setText(data.get(position).getInGroup_name());
//        holder.inUnsettle_no.setText(data.get(position).getInUnsettle_no());
//        holder.inSettle_no.setText(data.get(position).getInUnsettle_no());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentGroupName =holder.inGroup_name.getText().toString();
                ModelGroup mg= new ModelGroup();
//                String currentGroupID = mg.getInGroup_id();
//                System.out.println("Srij:" + currentGroupID);

                Context context = view.getContext();
                Intent intent = new Intent(context,Group_event_member.class);
                Intent intent1 = new Intent(context,FragmentEvent.class);
                intent1.putExtra("currentGroupName", currentGroupName);
                intent.putExtra("currentGroupName", currentGroupName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
