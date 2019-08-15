package com.example.x_splitter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AdapterUnequalSplit extends RecyclerView.Adapter<AdapterUnequalSplit.UnequalSplitViewHolder> {
    private Context context;
    public ArrayList<ModelUnequalSplit> data = new ArrayList<>();
    public HashMap<String,String> hashMap = new HashMap<>();


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
        holder.shares.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                hashMap.put(holder.member_name.getText().toString(), str);


            }

            @Override
            public void afterTextChanged(Editable s) {
//                hashMap.put(holder.member_name.getText().toString(), s.toString());
//                getHash(hashMap);

            }



        });
//        System.out.println("neha "+ hashMap);


    }

    public HashMap<String,String> getHash()
    {
        return hashMap;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
