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

public class FragmentEvent extends Fragment {
    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.group_event_fragment,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.group_event_recycler_view);
        AdapterHomeEvent adapterHomeEvent = new AdapterHomeEvent(this.context,Event.getEventData());
        recyclerView.setAdapter(adapterHomeEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        return view;
    }

    public FragmentEvent(){

    }
}
