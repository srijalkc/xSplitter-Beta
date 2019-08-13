package com.example.x_splitter;

public class ModelAddEvent {
    String groupName;
    Boolean isSelectedEvent;
    String ID;

    public ModelAddEvent(String groupName) {
        this.groupName = groupName;
    }

    ModelAddEvent(){

    }

    public ModelAddEvent(String groupName, Boolean isSelectedEvent) {
        this.groupName = groupName;
        this.isSelectedEvent = isSelectedEvent;
    }

    public ModelAddEvent(String groupName, Boolean isSelectedEvent, String ID) {
        this.groupName = groupName;
        this.isSelectedEvent = isSelectedEvent;
        this.ID = ID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getSelectedEvent() {
        return isSelectedEvent;
    }

    public void setSelectedEvent(Boolean selectedEvent) {
        isSelectedEvent = selectedEvent;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
