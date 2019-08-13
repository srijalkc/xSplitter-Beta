package com.example.x_splitter;

public class ModelUnequalSplit {
    private String member_name;
    private String member_share;

    public ModelUnequalSplit(String member_name, String member_share) {
        this.member_name = member_name;
        this.member_share = member_share;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_share() {
        return member_share;
    }

    public void setMember_share(String member_share) {
        this.member_share = member_share;
    }
}
