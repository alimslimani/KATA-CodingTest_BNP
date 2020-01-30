package com.bnp.coding.controllers;

import com.bnp.coding.models.Pricing;
import com.bnp.coding.models.Zone;
import com.bnp.coding.models.Trips;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20
 */

public class ZoneIntersectionControllerImpl implements ZoneIntersectionController {

    @Override
    public void findIntersectionBetweenTwoZones(Trips trips) {
        // Zone to same Zone
        if ((Zone.ZONE_1.getStations().contains(trips.getStationStart())) && (Zone.ZONE_1.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 1, 1, Pricing.TravelWithinZone1And2);
        } else if ((Zone.ZONE_2.getStations().contains(trips.getStationStart())) && (Zone.ZONE_2.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 2, 2, Pricing.TravelWithinZone1And2);
        } else if ((Zone.ZONE_3.getStations().contains(trips.getStationStart())) && (Zone.ZONE_3.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 3, 3, Pricing.TravelWithinZones3And4);
        } else if ((Zone.ZONE_4.getStations().contains(trips.getStationStart())) && (Zone.ZONE_4.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 4, 4, Pricing.TravelWithinZones3And4);
        } else if ((Zone.ZONE_2.getStations().contains(trips.getStationStart())) && (Zone.ZONE_1.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 2, 1, Pricing.TravelWithinZone1And2);
        } else if ((Zone.ZONE_4.getStations().contains(trips.getStationStart())) && (Zone.ZONE_3.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 4, 3, Pricing.TravelWithinZones3And4);
        }

// Zone to another Zone
//                1) Travel within zones 1 and 2: €2.40 per trip.
        else if ((Zone.ZONE_1.getStations().contains(trips.getStationStart())) && (Zone.ZONE_2.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 1, 2, Pricing.TravelWithinZone1And2);
        }
//                2) Travel within zones 3 and 4: €2.00 per trip.
        else if ((Zone.ZONE_3.getStations().contains(trips.getStationStart())) && (Zone.ZONE_4.getStations().contains(trips.getStationEnd()))) {
            setZoneAndCost(trips, 3, 4, Pricing.TravelWithinZones3And4);
        }
//                3) Travel from zone 3 to zone 1 or 2: €2.80 per trip.
        else if ((Zone.ZONE_3.getStations().contains(trips.getStationStart())) && (Zone.ZONE_1.getStations().contains(trips.getStationEnd()) || Zone.ZONE_2.getStations().contains(trips.getStationEnd()))) {
            trips.setZoneFrom(3);
            setZoneTo1Or2(trips);
            trips.setCostInCents((int) (Pricing.EuroToCents * Pricing.TravelFromZone3ToZone1Or2));
        }
//                4) Travel from zone 4 to Zone 1 or 2: €3.00 per trip.
        else if ((Zone.ZONE_4.getStations().contains(trips.getStationStart())) && (Zone.ZONE_1.getStations().contains(trips.getStationEnd()) || Zone.ZONE_2.getStations().contains(trips.getStationEnd()))) {
            trips.setZoneFrom(4);
            setZoneTo1Or2(trips);
            trips.setCostInCents((int) (Pricing.EuroToCents * Pricing.TravelFromZone4ToZone1Or2));
        }
//                5) Travel from zone 1 or 2, to zone 3: €2.80 per trip.
        else if ((Zone.ZONE_1.getStations().contains(trips.getStationStart()) || Zone.ZONE_2.getStations().contains(trips.getStationStart())) && (Zone.ZONE_3.getStations().contains(trips.getStationEnd()))) {
            setZoneFrom1Or2(trips);
            trips.setZoneTo(3);
            trips.setCostInCents((int) (Pricing.EuroToCents * Pricing.TravelFromZone1Or2ToZone3));
        }
//                6) Travel from zone 1 or 2, zone 4 to: €3.00 per trip.
        else if ((Zone.ZONE_1.getStations().contains(trips.getStationStart()) || Zone.ZONE_2.getStations().contains(trips.getStationStart())) && (Zone.ZONE_4.getStations().contains(trips.getStationEnd()))) {
            setZoneFrom1Or2(trips);
            trips.setZoneTo(4);
            trips.setCostInCents((int) (Pricing.EuroToCents * Pricing.TravelFromZone1Or2ToZone4));
        }
        //        7) If there is the possibility of two prices then we must charge the customer the lowest amount and reflect this in the pricing information.
    }

    private void setZoneTo1Or2(Trips trips) {
        if (Zone.ZONE_1.getStations().contains(trips.getStationEnd())) {
            trips.setZoneTo(1);
        } else {
            trips.setZoneTo(2);
        }
    }

    private void setZoneFrom1Or2(Trips trips) {
        if (Zone.ZONE_1.getStations().contains(trips.getStationStart())) {
            trips.setZoneFrom(1);
        } else {
            trips.setZoneFrom(2);
        }
    }

    private void setZoneAndCost(Trips trips, int zoneFrom, int zoneTo, double travelWithinZone1And2) {
        trips.setZoneFrom(zoneFrom);
        trips.setZoneTo(zoneTo);
        trips.setCostInCents((int) (Pricing.EuroToCents * travelWithinZone1And2));
    }
}
