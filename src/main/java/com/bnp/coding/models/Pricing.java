package com.bnp.coding.models;

public interface Pricing {
    static int EuroToCents = 100;
    static double TravelWithinZone1And2 = 2.4;
    static double TravelWithinZones3And4 = 2.00;
    static double TravelFromZone3ToZone1Or2 = 2.80;
    static double TravelFromZone4ToZone1Or2 = 3.00;
    static double TravelFromZone1Or2ToZone3 = 2.80;
    static double TravelFromZone1Or2ToZone4 = 3.00;
}
