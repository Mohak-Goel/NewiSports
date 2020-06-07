package com.example.myapplication;

import android.widget.RadioGroup;

import java.io.Serializable;

public class CreateEvent implements Serializable {

    String EventName;
    String FieldName;
    String CityName;
    String PostalCode;
    String SportsName;
    String chooseTime;
    String etDate;
    String EventDescription;
    String mImageUrl;
    String urlLink;
    String OurContact;
    boolean food;
    boolean lodging;
    boolean transport;

    public CreateEvent(String eventName, String fieldName, String cityName, String postalCode, String sportsName, String chooseTime, String etDate, RadioGroup food, RadioGroup lodging, RadioGroup transport, String eventdescrip, String ourContact, String imageURL) {
    }


    public void setOurContact(String ourContact) {
        OurContact = ourContact;
    }

    public String getOurContact() {
        return OurContact;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }


    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getEventName() {
        return EventName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public void setLodging(boolean lodging) {
        this.lodging = lodging;
    }

    public void setTransport(boolean transport) {
        this.transport = transport;
    }

    public boolean isFood() {
        return food;
    }

    public boolean isLodging() {
        return lodging;
    }

    public boolean isTransport() {
        return transport;
    }

    public void setEvent_Name(String event_Name) {
        EventName = event_Name;
    }

    public void setField_Name(String field_Name) {
        FieldName = field_Name;
    }

    public void setCity_Name(String city_Name) {
        CityName = city_Name;
    }

    public void setPostal_Code(String postal_Code) {
        PostalCode = postal_Code;
    }

    public void setSports_Name(String sports_Name) {
        SportsName = sports_Name;
    }

    public void setChoose_Time(String choose_Time) {
        this.chooseTime = choose_Time;
    }

    public void setEt_Date(String et_Date) {
        this.etDate = et_Date;
    }



    public String getField_Name() {
        return FieldName;
    }

    public String getCity_Name() {
        return CityName;
    }

    public String getPostal_Code() {
        return PostalCode;
    }

    public String getSports_Name() {
        return SportsName;
    }

    public String getChoose_Time() {
        return chooseTime;
    }

    public String getEt_Date() {
        return etDate;
    }


    public CreateEvent(String eventName, String fieldLocation, String cityName, String postalCode, String sportsName,
                       String chooseTime, String etDate,boolean food,boolean lodging,boolean transport,
                       String eventdescrip,String ourContact,String imageURL) {


        EventName = eventName;
        FieldName = fieldLocation;
        CityName = cityName;
        PostalCode = postalCode;
        SportsName = sportsName;
        this.chooseTime = chooseTime;
        this.etDate = etDate;
        this.food=food;
        this.lodging=lodging;
        this.transport=transport;
        urlLink=imageURL;
        EventDescription=eventdescrip;
        OurContact=ourContact;
    }

}
