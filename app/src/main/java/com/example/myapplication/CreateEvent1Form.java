package com.example.myapplication;

import java.io.Serializable;

public class CreateEvent1Form implements Serializable {

    String EventName;
    String FieldName;
    String CityName;
    String PostalCode;
    String SportsName;
    String chooseTime;
    String etDate;

    public CreateEvent1Form() {
    }


    public String getEventName() {
        return EventName;
    }

    public String getFieldName() {
        return FieldName;
    }

    public String getCityName() {
        return CityName;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public String getSportsName() {
        return SportsName;
    }

    public String getChooseTime() {
        return chooseTime;
    }

    public String getEtDate() {
        return etDate;
    }


    public CreateEvent1Form(String eventName, String fieldName, String cityName,
                            String postalCode, String sportsName, String chooseTime, String etDate) {
        EventName = eventName;
        FieldName = fieldName;
        CityName = cityName;
        PostalCode = postalCode;
        SportsName = sportsName;
        this.chooseTime = chooseTime;
        this.etDate = etDate;
    }

}
