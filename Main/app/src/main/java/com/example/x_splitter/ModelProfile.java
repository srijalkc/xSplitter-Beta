package com.example.x_splitter;

public class ModelProfile {
    private String friendName;
    private String friendEmail;

    public ModelProfile(String friendName, String friendEmail) {
        this.friendName = friendName;
        this.friendEmail = friendEmail;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }
}
