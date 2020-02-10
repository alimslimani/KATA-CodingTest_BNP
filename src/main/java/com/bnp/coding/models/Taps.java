package com.bnp.coding.models;

import com.google.gson.Gson;

public class Taps {

    private long unixTimestamp;
    private int customerId;
    private String station;

    public Taps() {
    }

    public Taps(long unixTimestamp, int customerId, String station) {
        this.unixTimestamp = unixTimestamp;
        this.customerId = customerId;
        this.station = station;
    }

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public void setUnixTimestamp(long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

