package com.example.myapplication;

import java.util.ArrayList;

public class ParticipantUniversity {

    private String participantUnivName, participantUnivAddress, participantUnivCity, participantUnivState,
            participantUnivPostalCode, participantUnivPhNo, participantUnivEmail, participantUnivCoachName,
            participantUnivCoachPhNo, participantUnivCoachEmail, eventid;

    private ArrayList<ParticipantItem> participantList;

    private boolean ptransport, pfood, plodging;

    public String getParticipantUnivName() {
        return participantUnivName;
    }

    public void setParticipantUnivName(String participantUnivName) {
        this.participantUnivName = participantUnivName;
    }

    public String getParticipantUnivAddress() {
        return participantUnivAddress;
    }

    public void setParticipantUnivAddress(String participantUnivAddress) {
        this.participantUnivAddress = participantUnivAddress;
    }

    public String getParticipantUnivCity() {
        return participantUnivCity;
    }

    public void setParticipantUnivCity(String participantUnivCity) {
        this.participantUnivCity = participantUnivCity;
    }

    public String getParticipantUnivState() {
        return participantUnivState;
    }

    public void setParticipantUnivState(String participantUnivState) {
        this.participantUnivState = participantUnivState;
    }

    public String getParticipantUnivPostalCode() {
        return participantUnivPostalCode;
    }

    public void setParticipantUnivPostalCode(String participantUnivPostalCode) {
        this.participantUnivPostalCode = participantUnivPostalCode;
    }

    public String getParticipantUnivPhNo() {
        return participantUnivPhNo;
    }

    public void setParticipantUnivPhNo(String participantUnivPhNo) {
        this.participantUnivPhNo = participantUnivPhNo;
    }

    public String getParticipantUnivEmail() {
        return participantUnivEmail;
    }

    public void setParticipantUnivEmail(String participantUnivEmail) {
        this.participantUnivEmail = participantUnivEmail;
    }

    public String getParticipantUnivCoachName() {
        return participantUnivCoachName;
    }

    public void setParticipantUnivCoachName(String participantUnivCoachName) {
        this.participantUnivCoachName = participantUnivCoachName;
    }

    public String getParticipantUnivCoachPhNo() {
        return participantUnivCoachPhNo;
    }

    public void setParticipantUnivCoachPhNo(String participantUnivCoachPhNo) {
        this.participantUnivCoachPhNo = participantUnivCoachPhNo;
    }

    public String getParticipantUnivCoachEmail() {
        return participantUnivCoachEmail;
    }

    public void setParticipantUnivCoachEmail(String participantUnivCoachEmail) {
        this.participantUnivCoachEmail = participantUnivCoachEmail;
    }

    public ArrayList<ParticipantItem> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(ArrayList<ParticipantItem> participantList) {
        this.participantList = participantList;
    }

    public boolean isPtransport() {
        return ptransport;
    }

    public void setPtransport(boolean ptransport) {
        this.ptransport = ptransport;
    }

    public boolean isPfood() {
        return pfood;
    }

    public void setPfood(boolean pfood) {
        this.pfood = pfood;
    }

    public boolean isPlodging() {
        return plodging;
    }

    public void setPlodging(boolean plodging) {
        this.plodging = plodging;
    }

    public ParticipantUniversity(String participantUnivName, String participantUnivAddress, String participantUnivCity, String participantUnivState, String participantUnivPostalCode, String participantUnivPhNo, String participantUnivEmail, String participantUnivCoachName, String participantUnivCoachPhNo, String participantUnivCoachEmail, boolean plodging, ArrayList<ParticipantItem> participantList, boolean ptransport, boolean pfood, String eventid) {
        this.participantUnivName = participantUnivName;
        this.participantUnivAddress = participantUnivAddress;
        this.participantUnivCity = participantUnivCity;
        this.participantUnivState = participantUnivState;
        this.participantUnivPostalCode = participantUnivPostalCode;
        this.participantUnivPhNo = participantUnivPhNo;
        this.participantUnivEmail = participantUnivEmail;
        this.participantUnivCoachName = participantUnivCoachName;
        this.participantUnivCoachPhNo = participantUnivCoachPhNo;
        this.participantUnivCoachEmail = participantUnivCoachEmail;
        this.participantList = participantList;
        this.ptransport = ptransport;
        this.pfood = pfood;
        this.plodging = plodging;
        this.eventid = eventid;
    }

    public ParticipantUniversity() {
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }
}
