package com.example.x_splitter;

public class ModelHomeEvent {

    private String EventName;
    private String SettleStatus;
    private String ToPayAmt;
    private String ToReceiveAmt;

    public ModelHomeEvent(String eventName, String settleStatus, String toPayAmt, String toReceiveAmt) {
        EventName = eventName;
        SettleStatus = settleStatus;
        ToPayAmt = toPayAmt;
        ToReceiveAmt = toReceiveAmt;
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

    public void setSettleStatus(String settleStatus) {
        SettleStatus = settleStatus;
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
