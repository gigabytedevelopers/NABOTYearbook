package com.gigabytedevelopersinc.apps.botany.classofchamps18.models;

import java.net.URL;

public class StudentsModel {
   private String studentName;
   private String studentQuotes;
   private String studentImage;
   private int studentIm;
   private int studentRegNo;
   private URL imageURL;
   private String fbURL;
   private String twURL;
   private String callPhone;
   private String dob;
   private String emailAddress;
   private String localGov;
   private String state;
   private String nickName;

    public StudentsModel(String studentName, String studentQuotes, String studentImage, int studentRegNo, String callPhone, String fbURL, String twURL){
        this.studentName = studentName;
        this.studentQuotes = studentQuotes;
        this.studentImage = studentImage;
        this.callPhone = callPhone;
        this.studentRegNo = studentRegNo;
        this.fbURL = fbURL;
        this.twURL = twURL;
    }

    public StudentsModel(String studentName, String studentQuotes, int studentIm, int studentRegNo, String callPhone, String fbURL, String twURL){
        this.studentName = studentName;
        this.studentQuotes = studentQuotes;
        this.studentIm = studentIm;
        this.callPhone = callPhone;
        this.studentRegNo = studentRegNo;
        this.fbURL = fbURL;
        this.twURL = twURL;

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

    public StudentsModel(String studentName, String studentQuotes, int studentIm, String callPhone,
                         String dob, String emailAddress, String localGov, String state, String nickName ){
        this.studentName = studentName;
        this.studentQuotes = studentQuotes;
        this.studentIm = studentIm;
        this.callPhone = callPhone;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.localGov = localGov;
        this.state = state;
        this.nickName = nickName;

    }

    public StudentsModel(String studentName, String studentQuotes, String studentImage, String callPhone,
                         String dob,String emailAddress,String localGov,String state,String nickName ){
        this.studentName = studentName;
        this.studentQuotes = studentQuotes;
        this.studentImage = studentImage;
        this.callPhone = callPhone;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.localGov = localGov;
        this.state = state;
        this.nickName = nickName;

    }


    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public int getStudentIm() {
        return studentIm;
    }

    public void setStudentIm(int studentIm) {
        this.studentIm = studentIm;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getFbURL() {
        return fbURL;
    }

    public void setFbURL(String fbURL) {
        this.fbURL = fbURL;
    }

    public String getTwURL() {
        return twURL;
    }

    public void setTwURL(String twURL) {
        this.twURL = twURL;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentQuotes() {
        return studentQuotes;
    }

    public void setStudentQuotes(String studentQuotes) {
        this.studentQuotes = studentQuotes;
    }

    public int getStudentRegNo() {
        return studentRegNo;
    }

    public void setStudentRegNo(int studentRegNo) {
        this.studentRegNo = studentRegNo;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }


}
