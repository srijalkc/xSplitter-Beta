package com.example.x_splitter;

public class ModelAddEvent {
    String groupName;
    Boolean isSelectedEvent;

    ModelAddEvent(){

    }

    public ModelAddEvent(String groupName, Boolean isSelectedEvent) {
        this.groupName = groupName;
        this.isSelectedEvent = isSelectedEvent;
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
}
