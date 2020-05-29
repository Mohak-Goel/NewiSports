package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class participantFormItem implements Serializable {

    private String participantUnivName, participantUnivAddress, participantUnivCity, participantUnivState,
            participantUnivPostalCode, participantUnivPhNo, participantUnivEmail, participantUnivCoachName,
            participantUnivCoachPhNo, participantUnivCoachEmail;

    private ArrayList<ParticipantItem> participantList;

    private boolean ptransport, pfood, plodging;

    public participantFormItem
            (String UnivName, String UnivAddress, String UnivCity, String UnivState,
             String UnivPostalCode, String UnivPhNo, String UnivEmail, String UnivCoachName,
             String UnivCoachPhNo, String UnivCoachEmail, boolean transport, boolean food, boolean lodging){

        participantUnivName = UnivName; participantUnivAddress = UnivAddress;   participantUnivCity = UnivCity;
        participantUnivState = UnivState;participantUnivPostalCode = UnivPostalCode;participantUnivPhNo = UnivPhNo;
        participantUnivEmail = UnivEmail; participantUnivCoachName = UnivCoachName; participantUnivCoachPhNo = UnivCoachPhNo;
        participantUnivCoachEmail = UnivCoachEmail; ptransport = transport; pfood = food; plodging = lodging;

    }


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

    public ArrayList<ParticipantItem> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(ArrayList<ParticipantItem> participantList) {
        this.participantList = participantList;
    }

}
