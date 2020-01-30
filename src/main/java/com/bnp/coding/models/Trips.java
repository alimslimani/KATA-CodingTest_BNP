package com.bnp.coding.models;

import com.google.gson.Gson;

public class Trips {

    private String stationStart, stationEnd;
    private long startedJourneyAt;
    private int costInCents;
    private int zoneFrom, zoneTo;

    public Trips() {
    }


    public Trips(String stationStart, String stationEnd, long startedJourneyAt, int costInCents, int zoneFrom, int zoneTo) {
        this.stationStart = stationStart;
        this.stationEnd = stationEnd;
        this.startedJourneyAt = startedJourneyAt;
        this.costInCents = costInCents;
        this.zoneFrom = zoneFrom;
        this.zoneTo = zoneTo;
    }

    public String getStationStart() {
        return stationStart;
    }

    public void setStationStart(String stationStart) {
        this.stationStart = stationStart;
    }

    public String getStationEnd() {
        return stationEnd;
    }

    public void setStationEnd(String stationEnd) {
        this.stationEnd = stationEnd;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public void setCostInCents(int costInCents) {
        this.costInCents = costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public void setZoneFrom(int zoneFrom) {
        this.zoneFrom = zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }

    public void setZoneTo(int zoneTo) {
        this.zoneTo = zoneTo;
    }

    public long getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public void setStartedJourneyAt(long startedJourneyAt) {
        this.startedJourneyAt = startedJourneyAt;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
