package com.example.isiapp;

public class triggers {
 String tri_uid ;
    String       tri_title ;
    String tri_description ;
            String st_type;
    String st_condition;
        int st_position;

    public triggers(String tri_uid) {
        this.tri_uid = tri_uid;
    }

    public String getTri_uid() {
        return tri_uid;
    }

    public void setTri_uid(String tri_uid) {
        this.tri_uid = tri_uid;
    }
}
