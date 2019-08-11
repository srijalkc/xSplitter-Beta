package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentUnequalSplit extends DialogFragment {

    ArrayList<ModelUnequalSplit> memberData = getMemberData();
    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.unequal_split_popup,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_split_unequal);
        AdapterUnequalSplit adapterGroup = new AdapterUnequalSplit(this.context,memberData);
        recyclerView.setAdapter(adapterGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        this.getDialog().setTitle("Enter shares");

        return view;
    }

    public static ArrayList<ModelUnequalSplit> getMemberData(){
        ArrayList<ModelUnequalSplit> modelUnequalSplits = new ArrayList<>();
        modelUnequalSplits.clear();
        modelUnequalSplits.add(new ModelUnequalSplit("Kathford","4"));
        modelUnequalSplits.add(new ModelUnequalSplit("Aimless","2"));
        modelUnequalSplits.add(new ModelUnequalSplit("Lolwa","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Gang","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Adhikari","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Maharjan","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Kathford","4"));
        modelUnequalSplits.add(new ModelUnequalSplit("Aimless","2"));
        modelUnequalSplits.add(new ModelUnequalSplit("Lolwa","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Gang","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Adhikari","3"));
        modelUnequalSplits.add(new ModelUnequalSplit("Maharjan","3"));

        return modelUnequalSplits;
    }

}
