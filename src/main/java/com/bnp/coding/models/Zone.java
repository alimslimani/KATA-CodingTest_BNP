package com.bnp.coding.models;

public enum Zone {
    ZONE_1("A B"),
    ZONE_2("C D E"),
    ZONE_3("C E F"),
    ZONE_4("F G H I");

    private final String stations;

    Zone(String stations) {
        this.stations = stations;
    }

    public String getStations() {
        return stations;
    }
}
