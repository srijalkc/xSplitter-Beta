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
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentMember extends Fragment {
    View view;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.group_member_fragment,container,false);

        TextView member_num = view.findViewById(R.id.member_num);

        RecyclerView recyclerView = view.findViewById(R.id.group_member_recycler_view);
        AdapterMember adapterMember = new AdapterMember(this.context,getMemberData());
        recyclerView.setAdapter(adapterMember);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

        member_num.setText(String.valueOf(adapterMember.getItemCount()));
        return view;
    }

    public static ArrayList<ModelMember> getMemberData(){
        ArrayList<ModelMember> modelMembers = new ArrayList<>();

        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Neha"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Nam"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Ruxana"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Srijal"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Ram"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Sita"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo,"Sneha"));
        modelMembers.add(new ModelMember(R.mipmap.ic_applogo_round,"Gita"));


        return modelMembers;
    }
}
