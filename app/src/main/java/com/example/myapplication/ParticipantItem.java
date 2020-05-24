package com.example.myapplication;

public class ParticipantItem {
    private String participantName, bloodGroup, sportName;


    public ParticipantItem(String pName, String blg, String spName)
    {
        participantName = pName;
        bloodGroup = blg;
        sportName = spName;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getSportName() {
        return sportName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }
}
