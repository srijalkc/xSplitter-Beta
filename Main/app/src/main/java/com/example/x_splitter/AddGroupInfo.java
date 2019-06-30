package com.example.x_splitter;

import java.util.ArrayList;

public class AddGroupInfo {
    ArrayList friends = new ArrayList();

    AddGroupInfo(ArrayList<String> al){
        for(int i = 0; i < al.size(); i++){
            String a = al.get(i);
            friends.add(a);
        }
    }

}
