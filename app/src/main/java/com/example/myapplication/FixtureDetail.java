package com.example.myapplication;

import java.util.Objects;

public class FixtureDetail {
    String participant1, participant2, fixtureDate, fixtureTime;

    public String getParticipant1() {
        return participant1;
    }

    public void setParticipant1(String participant1) {
        this.participant1 = participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    public void setParticipant2(String participant2) {
        this.participant2 = participant2;
    }

    public String getFixtureDate() {
        return fixtureDate;
    }

    public void setFixtureDate(String fixtureDate) {
        this.fixtureDate = fixtureDate;
    }

    public String getFixtureTime() {
        return fixtureTime;
    }

    public void setFixtureTime(String fixtureTime) {
        this.fixtureTime = fixtureTime;
    }

    public FixtureDetail() {
    }

    public FixtureDetail(String participant1, String participant2, String fixtureDate, String fixtureTime) {
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.fixtureDate = fixtureDate;
        this.fixtureTime = fixtureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FixtureDetail)) return false;
        FixtureDetail that = (FixtureDetail) o;
        return Objects.equals(participant1, that.participant1) &&
                Objects.equals(participant2, that.participant2) &&
                Objects.equals(fixtureDate, that.fixtureDate) &&
                Objects.equals(fixtureTime, that.fixtureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant1, participant2, fixtureDate, fixtureTime);
    }
}
