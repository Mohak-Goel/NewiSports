package com.example.myapplication;

public class model {

    private String UniversityName,Notice;

    public model() {
    }

    public model(String universityName, String notice) {
        UniversityName = universityName;
        Notice = notice;
    }

    public String getUniversityName() {
        return UniversityName;
    }

    public void setUniversityName(String universityName) {
        UniversityName = universityName;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
}
