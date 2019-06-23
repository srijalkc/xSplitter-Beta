package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentTransac extends Fragment {
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_transac_fragment,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.event_transac_recycler_view);
        AdapterTransaction adapter = new AdapterTransaction(this.context,getTransactionData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        return view;
    }

    public static ArrayList<ModelTransaction> getTransactionData(){
        ArrayList<ModelTransaction> modelTransactions = new ArrayList<>();

        modelTransactions.add(new ModelTransaction("21-10-2013","Lunch","2300","Neha","Food"));
        modelTransactions.add(new ModelTransaction("20-11-2014","Skirt","130","Sammy","Dress"));
        modelTransactions.add(new ModelTransaction("01-01-2013","Bus ticket","230","Srijal","Fair"));
        modelTransactions.add(new ModelTransaction("22-02-2014","Dinner","1000","Ruxana","Food"));
        modelTransactions.add(new ModelTransaction("11-10-2014","Lunch","1300","Nam","Food"));
        modelTransactions.add(new ModelTransaction("12-09-2017","Shirt","300","Sneha","Dress"));
        modelTransactions.add(new ModelTransaction("13-03-2017","Pant","1200","Nena","Dress"));
        modelTransactions.add(new ModelTransaction("24-02-2017","Milk","45","Nishan","Food"));

        return modelTransactions;
    }
}
