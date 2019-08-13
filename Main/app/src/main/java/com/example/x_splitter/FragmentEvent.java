package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
