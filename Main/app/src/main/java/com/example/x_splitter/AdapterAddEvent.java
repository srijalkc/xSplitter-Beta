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

public class AdapterAddEvent extends RecyclerView.Adapter<AdapterAddEvent.AddEventViewHolder> {
    private Context context;
    private ArrayList<ModelAddEvent> dataEvent;
    ArrayList<ModelAddEvent> checkedGroups = new ArrayList<>();

    public AdapterAddEvent(Context context, ArrayList<ModelAddEvent> dataEvent) {
        this.context = context;
        this.dataEvent = dataEvent;
    }

    public class AddEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView addGroupTxt;
        CheckBox chk_add_group;
        ItemClickListener itemClickListener;

        public AddEventViewHolder(@NonNull View itemView) {
            super(itemView);
            addGroupTxt = itemView.findViewById(R.id.tv_group_name);
            chk_add_group = itemView.findViewById(R.id.chk_select_group);
            chk_add_group.setOnClickListener(this);
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
    public AdapterAddEvent.AddEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewEvent = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_add_event, null);
        return new AddEventViewHolder(viewEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddEvent.AddEventViewHolder holder, int position) {
        holder.addGroupTxt.setText(dataEvent.get(position).getGroupName());
        holder.chk_add_group.setChecked(dataEvent.get(position).getSelectedEvent());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chkEvent = (CheckBox) v;

                if(chkEvent.isChecked()){
                    checkedGroups.add(dataEvent.get(pos));
                }
                else if (!chkEvent.isChecked()){
                    checkedGroups.remove(dataEvent.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataEvent.size();
    }


}
