package com.bnp.coding.controllers;

import com.bnp.coding.models.Taps;
import com.bnp.coding.models.Trips;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20
 */

public interface CustomerController {
    void getCustomerIdAndStationsAndTime(List<Taps> tapsList, List<String> stations, List<Integer> customerIds);

    String getListOfCustomersFromInputFile(String filePathReader, ReaderFileController readerFileController, ZoneIntersectionController zoneIntersectionController, CustomerController customerController);

    List<Long> createTrips(List<String> customerStations, List<Long> customerUnixTimestamp, List<Trips> tripsList, ZoneIntersectionController zoneIntersectionController);

    JSONArray findCustomer(String filePathReader, ReaderFileController readerFileController, ZoneIntersectionController zoneIntersectionController);

    void createTripsAndCustomers(ZoneIntersectionController zoneIntersectionController, JSONArray listOfCustomer, List<String> stations, List<String> stationsOfCustomer, List<Long> unixTimestampOfCustomer, List<Integer> customerIds, List<Trips> tripsList);
}
