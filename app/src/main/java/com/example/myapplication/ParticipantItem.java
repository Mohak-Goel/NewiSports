package com.example.myapplication;

public class ParticipantItem {
    private String participantName, participantEmail, participantPhNo ,bloodGroup;


    public ParticipantItem(String pName, String email, String phNo, String blg)
    {
        participantName = pName;
        participantEmail = email;
        participantPhNo = phNo;
        bloodGroup = blg;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public String getParticipantPhNo() {
        return participantPhNo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public void setParticipantPhNo(String participantPhNo) {
        this.participantPhNo = participantPhNo;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }
}
