package com.bnp.coding.models;

public interface Pricing {
    int EuroToCents = 100;
    double TravelWithinZone1And2 = 2.4;
    double TravelWithinZones3And4 = 2.00;
    double TravelFromZone3ToZone1Or2 = 2.80;
    double TravelFromZone4ToZone1Or2 = 3.00;
    double TravelFromZone1Or2ToZone3 = 2.80;
    double TravelFromZone1Or2ToZone4 = 3.00;
}
