package com.example.myapplication;

public class Unotice {
    String noticeID;
    String noticeText;

    public Unotice(){

    }

    public Unotice(String noticeID, String noticeText) {
        this.noticeID = noticeID;
        this.noticeText = noticeText;
    }

    public String getNoticeID() {
        return noticeID;
    }

    public String getNoticeText() {
        return noticeText;
    }
}
