package com.example.x_splitter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentUnequalSplit extends DialogFragment {

    Context context;
    View view;
    FirebaseDatabase dbReference;
    ArrayList<String> paidByListTransaction;
    List<String> groupMembers;
    String GroupId;
    String GroupName;
    double size;
    ArrayList<ModelUnequalSplit> memberData;

    public FragmentUnequalSplit(String groupId, String groupname) {
        GroupId = groupId;
        GroupName = groupname;
    }



    @Nullable
    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.unequal_split_popup, container, false);

        memberData = getMemberData(GroupId, GroupName);
        TextView btn_ok = view.findViewById(R.id.btn_ok_unequal_split);
        TextView btn_cancel = view.findViewById(R.id.btn_cancel_unequal_split);
        RecyclerView recyclerView = view.findViewById(R.id.rv_split_unequal);
        AdapterUnequalSplit adapterGroup = new AdapterUnequalSplit(this.context, memberData);
        recyclerView.setAdapter(adapterGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        this.getDialog().setTitle("Enter shares");
        return view;
    }

    public ArrayList<ModelUnequalSplit> getMemberData(String id, String gname) {
        ArrayList<ModelUnequalSplit> modelUnequalSplits = new ArrayList<>();
        modelUnequalSplits.clear();
        paidByListTransaction = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("Groups").child(id).child(gname).child("Members")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        groupMembers = new ArrayList<>();
                        for(DataSnapshot snapshot4 : dataSnapshot.getChildren()){
                            groupMembers.add(snapshot4.getValue().toString());
                        }
                        paidByListTransaction.addAll(groupMembers);
//                        for(int i=0; i<groupMembers.size(); i++){
//                            System.out.println("Namkong" + groupMembers.get(i));
//                        }
                        for(int i=0; i< groupMembers.size();i++){
                            modelUnequalSplits.add(new ModelUnequalSplit(groupMembers.get(i), ""));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        return modelUnequalSplits;
    }
}



