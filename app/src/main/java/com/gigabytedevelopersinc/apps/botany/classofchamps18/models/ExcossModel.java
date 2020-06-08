package com.gigabytedevelopersinc.apps.botany.classofchamps18.models;

public class ExcossModel {
    private String imageUrl;
    private String excossName;
    private String excossOffice;
    private String excossInstagram;
    private String excossFacebook;
    private int imageResource;
    private String excossQuotes;
    private String callPhone;
    private String dob;
    private String emailAddress;
    private String localGov;
    private String state;
    private String nickName;

    public ExcossModel(String excossName, String excossOffice, String imageUrl, String excossFacebook,
                       String excossInstagram, String callPhone){
        this.excossName = excossName;
        this.excossOffice = excossOffice;
        this.imageUrl = imageUrl;
        this.excossFacebook = excossFacebook;
        this.excossInstagram = excossInstagram;
        this.callPhone = callPhone;
    }

    public ExcossModel(String excossName, String excossOffice, int imageResource, String excossFacebook,
                       String excossInstagram, String callPhone){
        this.excossName = excossName;
        this.excossOffice = excossOffice;
        this.imageResource = imageResource;
        this.excossFacebook = excossFacebook;
        this.excossInstagram = excossInstagram;
        this.callPhone = callPhone;
    }

    public ExcossModel(String excossName, String excossQuotes, int imageResource, String callPhone,
                       String dob, String emailAddress, String localGov, String state, String nickName){
        this.excossName = excossName;
        this.excossQuotes = excossQuotes;
        this.imageResource = imageResource;
        this.callPhone = callPhone;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.localGov = localGov;
        this.state = state;
        this.nickName = nickName;

    }

    public ExcossModel(String excossName, String excossQuotes, String imageUrl, String callPhone,
                       String dob, String emailAddress, String localGov, String state, String nickName){
        this.excossName = excossName;
        this.excossQuotes = excossQuotes;
        this.imageUrl = imageUrl;
        this.callPhone = callPhone;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.localGov = localGov;
        this.state = state;
        this.nickName = nickName;

    }


    public String getExcossQuotes() {
        return excossQuotes;
    }

    public void setExcossQuotes(String excossQuotes) {
        this.excossQuotes = excossQuotes;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExcossName() {
        return excossName;
    }

    public void setExcossName(String excossName) {
        this.excossName = excossName;
    }

    public String getExcossOffice() {
        return excossOffice;
    }

    public void setExcossOffice(String excossQuote) {
        this.excossOffice = excossQuote;
    }

    public String getExcossInstagram() {
        return excossInstagram;
    }

    public void setExcossInstagram(String excossInstagram) {
        this.excossInstagram = excossInstagram;
    }

    public String getExcossFacebook() {
        return excossFacebook;
    }

    public void setExcossFacebook(String excossFacebook) {
        this.excossFacebook = excossFacebook;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

}
