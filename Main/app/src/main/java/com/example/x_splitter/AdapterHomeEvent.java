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

public class AdapterHomeEvent extends RecyclerView.Adapter<AdapterHomeEvent.MyViewHolder> {

    ArrayList<ModelHomeEvent> Data;
    ArrayList<ModelHomeEvent> Data2;
    Context context;
    String ID;
    String EventID;
    String GroupID;

    public AdapterHomeEvent(Context context, ArrayList<ModelHomeEvent> data, ArrayList<ModelHomeEvent> data2){
        this.context = context;
        Data = data;
        Data2 = data2;
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
//        EventID = Data.get(position).getEventID();
//        GroupID = Data.get(position).getGroupID();
//        System.out.println("Uncle:"+GroupID);
//        System.out.println("Uncle:"+EventID);
        holder.SettleStatus.setText(Data.get(position).getSettleStatus());
        holder.ToPayAmt.setText(Data.get(position).getGroupID()); //typecasting double value to string to put in textview and use settect
        for(int i =0 ; i < Data2.size();i++ ) {
            holder.ToReceiveAmt.setText(Data2.get(i).getEventID());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ModelHomeEvent mhe = new ModelHomeEvent();
//                String currentEventID = mhe.getEventID();
//                String currentGroupID = mhe.getGroupID();
//                String currentGroupName = mhe.getGroupName();
//                System.out.println("Uncle:"+currentEventID);
//                System.out.println("Uncle:"+currentGroupName);
//                System.out.println("Uncle:"+currentGroupID);

                String currentEventName = holder.EventName.getText().toString();
                String currentEventID = holder.ToPayAmt.getText().toString();
                String currentGroupID= holder.ToReceiveAmt.getText().toString();
                Context context = view.getContext();
                Intent intent = new Intent(context,Event_transac_report.class);
                intent.putExtra("currentEventName", currentEventName);
                intent.putExtra("currentEventID", currentEventID);
                intent.putExtra("currentGroupID", currentGroupID);

//                intent.putExtra("currentEventID", currentEventID);
//                intent.putExtra("currentGroupID", currentGroupID);
//                intent.putExtra("currentGroupName", currentGroupName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
}
