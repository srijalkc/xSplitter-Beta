package com.example.x_splitter;

public class ModelAddGroup {
    private String email;
    private String username;
    private boolean isSelected;

    public ModelAddGroup(String username, boolean isSelected) {
        this.username = username;
        this.isSelected = isSelected;
    }


    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
