package com.example.x_splitter;

public class ModelHomeEvent {

    private String EventName;
    private String GroupName;
    private String SettleStatus;
    private String ToPayAmt;
    private String ToReceiveAmt;

    private String GroupID;
    private String EventID;

    public ModelHomeEvent(String eventName, String groupName, String settleStatus, String groupid, String toReceiveAmt) {
        EventName = eventName;
        GroupName = groupName;
<<<<<<< HEAD
        SettleStatus = settleStatus;
        GroupID = groupid;
        ToReceiveAmt= toReceiveAmt;
=======

>>>>>>> 48426b4e7f70fe7e80eaef2134bd5962c2d27b1f
    }

    public ModelHomeEvent(String eventID) {
        EventID = eventID;
    }

    public String getGroupID() {
//        System.out.println("Uncle:"+GroupID);
        return GroupID;
    }

    public String getEventID() {
System.out.println("Uncle:"+EventID);
        return EventID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getSettleStatus() {
        return SettleStatus;
    }

    public String getToPayAmt() {
        return ToPayAmt;
    }

    public void setToPayAmt(String toPayAmt) {
        ToPayAmt = toPayAmt;
    }

    public String getToReceiveAmt() {
        return ToReceiveAmt;
    }

    public void setToReceiveAmt(String toReceiveAmt) {
        ToReceiveAmt = toReceiveAmt;
    }
}
