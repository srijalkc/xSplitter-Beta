package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.TransactionViewHolder> {

    private Context context;
    private ArrayList<ModelTransaction> data = new ArrayList<>();

    public AdapterTransaction(Context context, ArrayList<ModelTransaction> data) {
        this.context = context;
        this.data = data;
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{

        TextView indate;
        TextView intransac_name;
        TextView intransac_money;
        TextView ing_member;
        TextView intransac_category;

        public TransactionViewHolder(View itemView){
            super(itemView);
            indate = itemView.findViewById(R.id.transac_date);
            intransac_name = itemView.findViewById(R.id.transac_name);
            intransac_money = itemView.findViewById(R.id.transac_money);
            ing_member = itemView.findViewById(R.id.transac_member);
            intransac_category = itemView.findViewById(R.id.transac_category);
        }
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_each_transac,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.indate.setText(data.get(position).getDate());
        holder.intransac_money.setText(data.get(position).getTransac_money());
        holder.intransac_category.setText(data.get(position).getTransac_category());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
