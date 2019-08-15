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

        SettleStatus = settleStatus;
        GroupID = groupid;
        ToReceiveAmt= toReceiveAmt;

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
