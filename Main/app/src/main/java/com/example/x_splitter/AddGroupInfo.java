//package com.example.x_splitter;
//
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//
//public class AddGroupInfo {
//    Map<String, Object> Email;
//
//    public AddGroupInfo(String group_name, String ID){
//
//    }
//
//    public AddGroupInfo(Map<String, Object> Emails){
//        Set set = (Set) Emails.entrySet();
//        Iterator iterator = set.iterator();
//        for (int i = 0; i < Emails.size(); i++){
//            while (iterator.hasNext()) {
//                Map.Entry mapEntry = (Map.Entry) iterator.next();
//                String j = (String) mapEntry.getKey();
//                Emails.put(j, "true");
//            }
//        }
//    }
//}