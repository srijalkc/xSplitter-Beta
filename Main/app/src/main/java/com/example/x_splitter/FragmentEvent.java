package com.example.x_splitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentEvent extends Fragment {
    Context context;
    View view;
    String ID;
    String GN;
    String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.group_event_fragment,container,false);
        Intent intent = getActivity().getIntent();
        RecyclerView recyclerView = view.findViewById(R.id.group_event_recycler_view);
//        id = intent.getStringExtra("ID");

        String str = intent.getStringExtra("currentGroupName");
        String id =intent.getStringExtra("currentGroupID");
//        System.out.println("NE:"+ str);
//        System.out.println("NE:"+ id);
        Event e = new Event();
        AdapterHomeEvent adapterHomeEvent = new AdapterHomeEvent(this.context,e.getEventData(), e.getEventData2());
        recyclerView.setAdapter(adapterHomeEvent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        return view;
    }

    public FragmentEvent(){

    }

//    public ArrayList<ModelHomeEvent> retrieveEvent(String id){
//        ArrayList<ModelHomeEvent> modelHomeEvents = new ArrayList<>();
//        FirebaseDatabase.getInstance().getReference("EventName").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Map<String, Object> eventdata = (Map<String, Object>) dataSnapshot.getValue();
//                String eventname = (String) Objects.requireNonNull(eventdata).get("EventName");
//                ID = dataSnapshot.child("GroupID").getValue().toString();
//                System.out.println("SSS:"+ ID);
//                FirebaseDatabase.getInstance().getReference("GroupName").child(ID).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        Map<String, Object> groupdata = (Map<String, Object>) dataSnapshot.getValue();
//                        // System.out.println(groupdata);
//                        GN = (String) Objects.requireNonNull(groupdata).get("GroupName");
//                        //  System.out.println(GN);
//                        if(id.equals(ID)){
//                        modelHomeEvents.add(new ModelHomeEvent(eventname, GN, "Not Settled", "123.0", "12.0"));
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        return modelHomeEvents;
//    }
}
