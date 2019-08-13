package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAddGroup extends RecyclerView.Adapter<AdapterAddGroup.AddGroupViewHolder> {
    private Context context;
    private ArrayList<ModelAddGroup> data;
    ArrayList<ModelAddGroup> checkedFriends = new ArrayList<>();

    public AdapterAddGroup(Context context, ArrayList<ModelAddGroup> data) {
        this.context = context;
        this.data = data;
    }


    public class AddGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView addFriendTxt;
        CheckBox chk_add_friend;
        ItemClickListener itemClickListener;

        public AddGroupViewHolder(View itemView){
            super(itemView);
            addFriendTxt = itemView.findViewById(R.id.tv_friend_name);
            chk_add_friend = itemView.findViewById(R.id.chk_select_friend);

            chk_add_friend.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }

    @NonNull
    @Override
    public AddGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_friendlist_item,null);
        return new AddGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddGroupViewHolder holder, int position) {
        holder.addFriendTxt.setText(data.get(position).getusername());
        holder.chk_add_friend.setChecked(data.get(position).isSelected());

        holder.setItemClickListener(new ItemClickListener(){
            public void onItemClick(View v, int pos){
                CheckBox chk = (CheckBox) v;

                //Check state
                if(chk.isChecked()){
                    checkedFriends.add(data.get(pos));
                }else if(!chk.isChecked()){
                    checkedFriends.remove(data.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
