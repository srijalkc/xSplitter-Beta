package com.example.x_splitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Object> items;
    private final int VERTICAL = 2;
    private final int HORIZONTAL = 1;

    public AdapterHome(Context context, ArrayList<Object> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case HORIZONTAL:
                view = inflater.inflate(R.layout.layout_home_group, parent, false);
                holder = new GroupViewHolder(view);
                break;
            case  VERTICAL:
                view = inflater.inflate(R.layout.layout_home_event, parent, false);
                holder = new EventViewHolder(view);
                break;

            default:
                view = inflater.inflate(R.layout.layout_home_group, parent, false);
                holder = new GroupViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == HORIZONTAL)
            groupView((GroupViewHolder) holder);
        else if (holder.getItemViewType() == VERTICAL)
            eventView((EventViewHolder) holder);
    }



    @Override
    public int getItemCount() {
        return  items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof ModelHomeGroup)
            return HORIZONTAL;
        if(items.get(position) instanceof ModelHomeEvent)
            return VERTICAL;
        return -1;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        EventViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.home_event_recycler_view);
        }
    }

    private void eventView(EventViewHolder holder) {
        Event e = new Event();
        AdapterHomeEvent adapter1 = new AdapterHomeEvent(this.context,Home.getEventData(),e.getEventData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapter1);
    }

    private void groupView(GroupViewHolder holder) {
        AdapterHomeGroup adapter = new AdapterHomeGroup(Home.getGroupData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        GroupViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.home_group_recycler_view);
        }
    }

}
