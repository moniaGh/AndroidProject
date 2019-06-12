package com.example.isiapp;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class stepOne {
    @SerializedName("step_uid")

    String step_uid;
    @SerializedName("step_type_obj")
    String     step_type_obj;
    @SerializedName("step_uid_obj")
    String    step_uid_obj;
    @SerializedName("step_condition")
    String step_condition;
    @SerializedName("step_position")
    int step_position;
    @SerializedName("step_mode")
    String step_mode;
    @SerializedName("obj_title")
    String obj_title;
    @SerializedName("obj_description")
    String  obj_description;
    @SerializedName("triggers")
   List<triggers> triggers;


    public String getStep_uid() {
        return step_uid;
    }

    public void setStep_uid(String step_uid) {
        this.step_uid = step_uid;
    }

    public String getStep_type_obj() {
        return step_type_obj;
    }

    public void setStep_type_obj(String step_type_obj) {
        this.step_type_obj = step_type_obj;
    }

    public String getStep_uid_obj() {
        return step_uid_obj;
    }

    public void setStep_uid_obj(String step_uid_obj) {
        this.step_uid_obj = step_uid_obj;
    }

    public String getStep_condition() {
        return step_condition;
    }

    public void setStep_condition(String step_condition) {
        this.step_condition = step_condition;
    }

    public int getStep_position() {
        return step_position;
    }

    public void setStep_position(int step_position) {
        this.step_position = step_position;
    }

    public String getStep_mode() {
        return step_mode;
    }

    public void setStep_mode(String step_mode) {
        this.step_mode = step_mode;
    }

    public String getObj_title() {
        return obj_title;
    }

    public void setObj_title(String obj_title) {
        this.obj_title = obj_title;
    }

    public String getObj_description() {
        return obj_description;
    }

    public void setObj_description(String obj_description) {
        this.obj_description = obj_description;
    }

    public List<com.example.isiapp.triggers> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<com.example.isiapp.triggers> triggers) {
        this.triggers = triggers;
    }
}
