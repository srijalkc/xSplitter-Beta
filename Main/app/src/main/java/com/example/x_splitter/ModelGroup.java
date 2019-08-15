package com.example.x_splitter;

public class ModelGroup {

    private int inGroup_image;
    private String inGroup_name;
    private String inGroup_id;
    private String inUnsettle_no;
    private String inSettle_no;
    String currentGroupName;

    public ModelGroup(){

    }
    public ModelGroup(int inGroup_image, String inGroup_name, String inGroup_id, String inUnsettle_no, String inSettle_no) {
        this.inGroup_image = inGroup_image;
        this.inGroup_name = inGroup_name;
        this.inGroup_id =inGroup_id;
        this.inUnsettle_no = inUnsettle_no ;
        this.inSettle_no = inSettle_no;
    }


    public String getInGroup_id() {
        return inGroup_id;
    }


    public int getInGroup_image() {
        return inGroup_image;
    }

    public void setInGroup_image(int inGroup_image) {
        this.inGroup_image = inGroup_image;
    }

    public String getInGroup_name() {
        return inGroup_name;
    }

    public void setInGroup_name(String inGroup_name) {
        this.inGroup_name = inGroup_name;
    }

//    public String getInUnsettle_no() {
//        return inUnsettle_no;
//    }
//
//    public void setInUnsettle_no(String inUnsettle_no) {
//        this.inUnsettle_no = inUnsettle_no;
//    }
//
//    public String getInSettle_no() {
//        return inSettle_no;
//    }
//
//    public void setInSettle_no(String inSettle_no) {
//        this.inSettle_no = inSettle_no;
//    }
}
