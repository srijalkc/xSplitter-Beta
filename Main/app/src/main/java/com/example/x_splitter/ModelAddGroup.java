package com.example.x_splitter;

public class ModelAddGroup {
    private String email;
    private boolean isSelected;

    public ModelAddGroup(String email, boolean isSelected) {
        this.email = email;
        this.isSelected = isSelected;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
