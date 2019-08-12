package com.example.x_splitter;

public class EventInfo {
    String ID;
    String EventName;

    public EventInfo(String groupID) {
        GroupID = groupID;
    }

    String GroupID;

    EventInfo(){
    }

    public EventInfo(String ID, String eventName, String groupID) {
        this.ID = ID;
        EventName = eventName;
        GroupID = groupID;
    }

}
