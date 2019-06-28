package com.example.x_splitter;

public class ModelMember {
    private String member_name;
    private int member_image;

    public ModelMember(int member_image, String member_name) {
        this.member_name = member_name;
        this.member_image = member_image;
    }


    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getMember_image() {
        return member_image;
    }

    public void setMember_image(int member_image) {
        this.member_image = member_image;
    }
}
