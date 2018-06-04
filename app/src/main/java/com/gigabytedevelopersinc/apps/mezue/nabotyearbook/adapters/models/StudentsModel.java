package com.gigabytedevelopersinc.apps.mezue.nabotyearbook.adapters.models;

import java.net.URL;

public class StudentsModel {
   private String studentName;
   private String studentQuotes;
   private int studentImage;
   private URL imageURL;

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

    public int getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(int studentImage) {
        this.studentImage = studentImage;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public StudentsModel(String studentName, String studentQuotes, int studentImage){
        this.studentName = studentName;
        this.studentQuotes = studentQuotes;
        this.studentImage = studentImage;

    }

}
