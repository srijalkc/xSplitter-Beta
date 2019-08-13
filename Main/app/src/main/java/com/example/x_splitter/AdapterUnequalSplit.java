package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUnequalSplit extends RecyclerView.Adapter<AdapterUnequalSplit.UnequalSplitViewHolder> {
    private Context context;
    private ArrayList<ModelUnequalSplit> data = new ArrayList<>();

    public AdapterUnequalSplit(Context context, ArrayList<ModelUnequalSplit> data) {
        this.context = context;
        this.data = data;
    }

    public class UnequalSplitViewHolder extends RecyclerView.ViewHolder {
        TextView member_name;
        EditText shares;

        public UnequalSplitViewHolder(View itemView) {
            super(itemView);
            member_name = itemView.findViewById(R.id.tv_unequal_split_member);
            shares = itemView.findViewById(R.id.unequal_split_member_share);
        }
    }

    @NonNull
    @Override
    public UnequalSplitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_unequal_split,parent,false);
        return new UnequalSplitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnequalSplitViewHolder holder, int position) {
        holder.member_name.setText(data.get(position).getMember_name());
        holder.shares.setText(data.get(position).getMember_share());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
