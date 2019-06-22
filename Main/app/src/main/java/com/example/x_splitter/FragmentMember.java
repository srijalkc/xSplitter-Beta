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

public class FragmentMember extends Fragment {
    View view;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.group_member_fragment,container,false);


        RecyclerView recyclerView = view.findViewById(R.id.group_member_recycler_view);
        AdapterMember adapterMember = new AdapterMember(this.context,getMemberData());
        recyclerView.setAdapter(adapterMember);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        return view;
    }

    public static ArrayList<ModelMember> getMemberData(){
        ArrayList<ModelMember> modelMembers = new ArrayList<>();

        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Neha"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Nam"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Ruxana"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Srija;"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Ram"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Sita"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Sneha"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Gita"));


        return modelMembers;
    }
}
