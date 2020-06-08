package com.gigabytedevelopersinc.apps.botany.classofchamps18.models;

public class BirthdayModel {
    private int imageResource;
    private String name;
    private String nickName;
    private String date;


    public BirthdayModel(int imageResource, String name, String nickName, String date){
        this.imageResource = imageResource;
        this.name = name;
        this.nickName = nickName;
        this.date = date;

    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
