package com.gigabytedevelopersinc.apps.mezue.classofchamps18.models;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/20/2018
 **/
public class StaffsModel {
    private String imageUrl;
    private String staffName;
    private String staffsOffice;
    private String callPhone;
    private String dob;
    private int imageResource;
    private String emailAddress;
    private String localGov;
    private String state;
    private String nickName;

    public StaffsModel (String staffName, String staffsOffice, String imageUrl, String callPhone) {
        this.staffName = staffName;
        this.staffsOffice = staffsOffice;
        this.imageUrl = imageUrl;
        this.callPhone = callPhone;
    }
    public StaffsModel (String staffName, String staffsOffice, int imageResource, String callPhone) {
        this.staffName = staffName;
        this.staffsOffice = staffsOffice;
        this.imageResource = imageResource;
        this.callPhone = callPhone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffsOffice() {
        return staffsOffice;
    }

    public void setStaffsOffice(String staffsOffice) {
        this.staffsOffice = staffsOffice;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
