package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentReport extends Fragment {

    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_report_fragment,container,false);



        RecyclerView recyclerView = view.findViewById(R.id.expenses_recycler_view);
        AdapterReport adapter = new AdapterReport(this.context,getExpenseData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

//        total.setText(String.valueOf(adapter.getItemCount())); //just to show number
        return view;
    }

    public static ArrayList<ModelReport> getExpenseData(){
        ArrayList<ModelReport> modelReports = new ArrayList<>();

        modelReports.add(new ModelReport("A",1234, "B" ));
        modelReports.add(new ModelReport("C",4567, "D"));
        modelReports.add(new ModelReport("E",34589, "F"));

        return modelReports;
    }
}
