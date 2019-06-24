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
        TextView incategory_name;
        TextView incategory_total;

        public ReportViewHolder(View itemView){
            super(itemView);
            incategory_name = itemView.findViewById(R.id.category_name);
            incategory_total = itemView.findViewById(R.id.category_total);
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
        holder.incategory_name.setText(data.get(position).getCategory_name());
        holder.incategory_total.setText(data.get(position).getCategory_total());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
