package com.gigabytedevelopersinc.apps.mezue.classofchamps18.models;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 8/15/2018
 * Edited By Emmanuel Okocha (Co-Founder and CTO at Gigabyte Developers) on 16/8/2018
 **/
public class FyF_Model {
    private String imageUrl;
    private String fyfExcossName;
    private String fyfExcossOffice;
    private String fyfExcosInstagram;
    private String fyfExcosFacebook;
    private String fyfQuotes;
    private String callPhone;
    private String dob;
    private int imageResource;
    private String emailAddress;
    private String localGov;
    private String state;
    private String nickName;

    public FyF_Model(String fyfExcossName, String fyfExcossOffice, String imageUrl, String fyfExcosFacebook, String fyfExcosInstagram, String callPhone){
        this.fyfExcossName = fyfExcossName;
        this.fyfExcossOffice = fyfExcossOffice;
        this.imageUrl = imageUrl;
        this.fyfExcosFacebook = fyfExcosFacebook;
        this.fyfExcosInstagram = fyfExcosInstagram;
        this.callPhone = callPhone;
    }

    public FyF_Model(String fyfExcossName, String fyfExcossOffice, int imageResource, String fyfExcosFacebook, String fyfExcosInstagram, String callPhone){
        this.fyfExcossName = fyfExcossName;
        this.fyfExcossOffice = fyfExcossOffice;
        this.imageResource = imageResource;
        this.fyfExcosFacebook = fyfExcosFacebook;
        this.fyfExcosInstagram = fyfExcosInstagram;
        this.callPhone = callPhone;
    }

    public FyF_Model(String fyfExcossName, String fyfQuotes, int imageResource, String callPhone,
                     String dob, String emailAddress, String localGov, String state, String nickName){

        this.fyfExcossName = fyfExcossName;
        this.fyfQuotes = fyfQuotes;
        this.imageResource = imageResource;
        this.callPhone = callPhone;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.localGov = localGov;
        this.state = state;
        this.nickName = nickName;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFyfExcossName() {
        return fyfExcossName;
    }

    public void setFyfExcossName(String fyfExcossName) {
        this.fyfExcossName = fyfExcossName;
    }

    public String getFyfExcossOffice() {
        return fyfExcossOffice;
    }

    public void setFyfExcossOffice(String fyfExcossOffice) {
        this.fyfExcossOffice = fyfExcossOffice;
    }

    public String getFyfExcosInstagram() {
        return fyfExcosInstagram;
    }

    public void setFyfExcosInstagram(String fyfExcosInstagram) {
        this.fyfExcosInstagram = fyfExcosInstagram;
    }

    public String getFyfExcosFacebook() {
        return fyfExcosFacebook;
    }

    public void setFyfExcosFacebook(String fyfExcosFacebook) {
        this.fyfExcosFacebook = fyfExcosFacebook;
    }

    public String getFyfQuotes() {
        return fyfQuotes;
    }

    public void setFyfQuotes(String fyfQuotes) {
        this.fyfQuotes = fyfQuotes;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLocalGov() {
        return localGov;
    }

    public void setLocalGov(String localGov) {
        this.localGov = localGov;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
