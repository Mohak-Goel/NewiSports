package com.example.myapplication;

public class Query {
    String noticeID;
    String noticeText;

    public Query(){

    }

    public Query(String noticeID, String noticeText) {
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
