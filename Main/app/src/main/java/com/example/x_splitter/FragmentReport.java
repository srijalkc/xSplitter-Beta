package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.core.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentReport extends Fragment {

    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_report_fragment,container,false);

        TextView total = view.findViewById(R.id.total_num);

        RecyclerView recyclerView = view.findViewById(R.id.expenses_recycler_view);
        AdapterReport adapter = new AdapterReport(this.context,getExpenseData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        total.setText(String.valueOf(adapter.getItemCount())); //just to show number
        return view;
    }

    public static ArrayList<ModelReport> getExpenseData(){
        ArrayList<ModelReport> modelReports = new ArrayList<>();

        modelReports.add(new ModelReport("Food","1234"));
        modelReports.add(new ModelReport("Dress","1304"));
        modelReports.add(new ModelReport("Fair","2439"));

        return modelReports;
    }
}
