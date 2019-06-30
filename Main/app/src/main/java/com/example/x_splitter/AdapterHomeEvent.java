package com.example.x_splitter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterHomeEvent extends RecyclerView.Adapter<AdapterHomeEvent.MyViewHolder> {

    ArrayList<ModelHomeEvent> Data = new ArrayList<>();
    Context context;

    public AdapterHomeEvent(Context context, ArrayList<ModelHomeEvent> data){
        this.context = context;
        Data = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView EventName;
        TextView GroupName;
        TextView SettleStatus;
        TextView ToPayAmt;
        TextView ToReceiveAmt;

        public MyViewHolder(View itemView) {
            super(itemView);
            EventName = itemView.findViewById(R.id.event_name);
            GroupName = itemView.findViewById(R.id.event_group_name);
            SettleStatus = itemView.findViewById(R.id.event_settle_status);
            ToPayAmt = itemView.findViewById(R.id.event_to_pay_amt);
            ToReceiveAmt = itemView.findViewById(R.id.event_to_receive_amt);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_event_item,parent,false);
        return new AdapterHomeEvent.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.EventName.setText(Data.get(position).getEventName()); //getter made in ModelHomeEvent
        holder.GroupName.setText(Data.get(position).getGroupName());
        holder.SettleStatus.setText(Data.get(position).getSettleStatus());
        holder.ToPayAmt.setText(Data.get(position).getToPayAmt()); //typecasting double value to string to put in textview and use settect
        holder.ToReceiveAmt.setText(Data.get(position).getToReceiveAmt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context,Event_transac_report.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
}
