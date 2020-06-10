package com.example.myapplication;

public class model2 {
    private String participantUnivName,participantUnivCoachName;

    public model2() {
    }

    public model2(String participantUnivName, String participantUnivCoachName) {
        this.participantUnivName = participantUnivName;
        this.participantUnivCoachName = participantUnivCoachName;
    }

    public String getParticipantUnivName() {
        return participantUnivName;
    }

    public void setParticipantUnivName(String participantUnivName) {
        this.participantUnivName = participantUnivName;
    }

    public String getParticipantUnivCoachName() {
        return participantUnivCoachName;
    }

    public void setParticipantUnivCoachName(String participantUnivCoachName) {
        this.participantUnivCoachName = participantUnivCoachName;
    }
}
