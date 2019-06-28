package com.example.x_splitter;

import java.util.ArrayList;

public class AddGroupInfo {
    String friends;
    AddGroupInfo(){

    }

    AddGroupInfo(ArrayList al){
        for(int i = 0; i < al.size(); i++){
            String a = (String) al.get(i);
            friends = a;
        }
    }

}
