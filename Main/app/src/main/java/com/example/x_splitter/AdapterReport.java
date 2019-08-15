package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterReport extends RecyclerView.Adapter<AdapterReport.ReportViewHolder> {

    private Context context;
    private ArrayList<ModelReport> data = new ArrayList<>();

    public AdapterReport(Context context, ArrayList<ModelReport> data) {
        this.context = context;
        this.data = data;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder{
        TextView user1;
        TextView user2;
        TextView amount;

        public ReportViewHolder(View itemView){
            super(itemView);
            user1 = itemView.findViewById(R.id.tv_user_one);
            amount = itemView.findViewById(R.id.tv_pay_amount);
            user2 = itemView.findViewById(R.id.tv_user_two);
        }
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_report_item,parent,false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        holder.user1.setText(data.get(position).getUser1());
        holder.amount.setText(Double.toString(data.get(position).getAmount()));
        holder.user2.setText(data.get(position).getUser2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
