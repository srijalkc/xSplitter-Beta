package com.example.x_splitter;

public class AddUserModel {
    String email;
    String username;
    boolean isSelectdUser;

    public AddUserModel(){

    }

    public AddUserModel(String email, String username, boolean isSelectdUser) {
        this.email = email;
        this.username = username;
        this.isSelectdUser = isSelectdUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getSelectdUser() {
        return isSelectdUser;
    }

    public void setSelectdUser(boolean selectdUser) {
        isSelectdUser = selectdUser;
    }
}
