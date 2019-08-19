package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHomeEvent extends RecyclerView.Adapter<AdapterHomeEvent.MyViewHolder> implements Filterable {

    ArrayList<ModelHomeEvent> Data;
    ArrayList<ModelHomeEvent> Data2;
    ArrayList<ModelHomeEvent> DataFiltered;
    Context context;
    String ID;
    String EventID;
    String GroupID;
    public static String id = null;
    public static String grpName = null;
    public static  String grpId = null;

    public AdapterHomeEvent(Context context, ArrayList<ModelHomeEvent> data, ArrayList<ModelHomeEvent> data2){
        this.context = context;
        Data = data;
        Data2 = data2;
        this.DataFiltered = data;
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
        holder.EventName.setText(DataFiltered.get(position).getEventName()); //getter made in ModelHomeEvent
        holder.GroupName.setText(DataFiltered.get(position).getGroupName());
        holder.SettleStatus.setText(DataFiltered.get(position).getSettleStatus());
        holder.ToPayAmt.setText(DataFiltered.get(position).getGroupID()); //typecasting double value to string to put in textview and use settect

        if(holder!= null && Data2.size() !=0) {
            holder.ToReceiveAmt.setText(Data2.get(position).getEventID());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentEventName = holder.EventName.getText().toString();
                String currentGroupName = holder.GroupName.getText().toString();
                String currentEventID = holder.ToPayAmt.getText().toString();
                String currentGroupID = holder.ToReceiveAmt.getText().toString();
//                System.out.println("Mumy:"+ currentEventID);
//                System.out.println("Mumy:"+ currentEventName);
//
//                System.out.println("Mumy:"+ currentGroupID);
//
//                System.out.println("Mumy:"+ currentGroupName);


                Context context = view.getContext();

//                final FragmentTransac  fr = new FragmentTransac(currentGroupID, currentEventID);
                Bundle bundle = new Bundle();
                bundle.putString("currentEventName", currentEventName);
                bundle.putString("currentEventID", currentEventID);
                bundle.putString("currentGroupID", currentGroupID);
                bundle.putString("currentGroupName", currentGroupName);
                FragmentReport fr = new FragmentReport();
                fr.setArguments(bundle);

                id = currentGroupID;
                grpId = currentEventID;
                grpName = currentGroupName;


               Intent intent = new Intent(context,Event_transac_report.class);
                intent.putExtra("currentEventName", currentEventName);
                intent.putExtra("currentEventID", currentEventID);
                intent.putExtra("currentGroupID", currentGroupID);
                intent.putExtra("currentGroupName", currentGroupName);
//
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if(Key.isEmpty()){
                    DataFiltered = Data;
                }

                else {
                    ArrayList<ModelHomeEvent> isFiltered = new ArrayList<>();
                    for(ModelHomeEvent row: Data){
                        if(row.getEventName().toLowerCase().contains(Key.toLowerCase())){
                            isFiltered.add(row);
                        }
                    }
                    DataFiltered = isFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = DataFiltered;
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                DataFiltered = (ArrayList<ModelHomeEvent>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}
