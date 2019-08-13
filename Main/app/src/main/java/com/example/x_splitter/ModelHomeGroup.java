package com.example.x_splitter;

public class ModelHomeGroup {

    private String GroupName;
    private int GroupImage;

    public ModelHomeGroup(String groupName, int groupImage) {
        GroupName = groupName;
        GroupImage = groupImage;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public int getGroupImage() {
        return GroupImage;
    }

    public void setGroupImage(int groupImage) {
        GroupImage = groupImage;
    }
}
