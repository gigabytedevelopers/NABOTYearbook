package com.gigabytedevelopersinc.apps.botany.classofchamps18.models;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/20/2018
 **/
public class StaffsModel {
    private String imageUrl;
    private String staffName;
    private String staffsOffice;
    private String phone;
    private int imageResource;

    public StaffsModel (String staffName, String staffsOffice, String imageUrl, String phone) {
        this.staffName = staffName;
        this.staffsOffice = staffsOffice;
        this.imageUrl = imageUrl;
        this.phone = phone;
    }
    public StaffsModel (String staffName, String staffsOffice, int imageResource, String phone) {
        this.staffName = staffName;
        this.staffsOffice = staffsOffice;
        this.imageResource = imageResource;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }*/

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
