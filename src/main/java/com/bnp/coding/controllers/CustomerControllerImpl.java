package com.bnp.coding.controllers;

import com.bnp.coding.Split;
import com.bnp.coding.models.Customer;
import com.bnp.coding.models.Taps;
import com.bnp.coding.models.Trips;
import com.google.gson.Gson;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20
 */

public class CustomerControllerImpl implements CustomerController {

    private static int getCost(List<Trips> tripsList) {
        int cost = 0;
        for (Trips t : tripsList) {
            cost = cost + t.getCostInCents();
        }
        return cost;
    }

    private static void createCustomer(JSONArray listOfCustomer, List<Integer> customerIds, List<Trips> tripsList, int j, int cost) {
        Customer customers = new Customer();
        customers.setCustomerId(customerIds.get(j));
        customers.setTrips(tripsList);
        customers.setTotalCostInCents(cost);
        listOfCustomer.put(customers);
    }

    @Override
    public void getCustomerIdAndStationsAndTime(List<Taps> tapsList, List<String> stations, List<Integer> customerIds) {
        tapsList.stream()
                .collect(Collectors.groupingBy(Taps::getCustomerId))
                .forEach((a, e) -> {
                    customerIds.add(a);
                    e.forEach(taps -> {
                                if (taps.getCustomerId() == a) {
                                    stations.add(a + " " + taps.getStation() + " " + taps.getUnixTimestamp());
                                }
                            }
                    );
                });
    }

    @Override
    public String getListOfCustomersFromInputFile(String filePathReader, ReaderFileController readerFileController, ZoneIntersectionController zoneIntersectionController, CustomerController customerController) {
        JSONArray listOfCustomer = customerController.findCustomer(filePathReader, readerFileController, zoneIntersectionController);
        Gson gsonConverter = new Gson();
        String result = gsonConverter.toJson(listOfCustomer);
        return result.replaceAll("myArrayList", "customerSummaries");
    }

    @Override
    public List<Long> createTrips(List<String> customerStations, List<Long> customerUnixTimestamp, List<Trips> tripsList, ZoneIntersectionController zoneIntersectionController) {
        List<String> stations = Split.split(customerStations, 2, x -> x.toString());
        List<String> times = Split.split(customerUnixTimestamp, 2, x -> x.toString());

        for (int i = 0; i < stations.size(); i++) {
            Trips trips = new Trips();
            trips.setStationStart(stations.get(i).substring(1, 2));
            trips.setStationEnd(stations.get(i).substring(4, 5));
            trips.setStartedJourneyAt(Long.valueOf(times.get(i).substring(1, times.get(i).indexOf(","))));
            zoneIntersectionController.findIntersectionBetweenTwoZones(trips);
            tripsList.add(trips);
        }
        customerUnixTimestamp = new ArrayList<>();
        return customerUnixTimestamp;
    }

    @Override
    public JSONArray findCustomer(String filePathReader, ReaderFileController readerFileController, ZoneIntersectionController zoneIntersectionController) {
        List<Taps> tapsList = readerFileController.getTapsObjectFromInputFile(filePathReader).stream().collect(toList());
        JSONArray listOfCustomer = new JSONArray();
        List<String> stations = new ArrayList<>();
        List<String> stationsOfCustomer = new ArrayList<>();
        List<Long> unixTimestampOfCustomer = new ArrayList<>();
        List<Integer> customerIds = new ArrayList<>();
        List<Trips> tripsList = new ArrayList<>();

        getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        return listOfCustomer;
    }

    @Override
    public void createTripsAndCustomers(ZoneIntersectionController zoneIntersectionController, JSONArray listOfCustomer, List<String> stations, List<String> stationsOfCustomer, List<Long> unixTimestampOfCustomer, List<Integer> customerIds, List<Trips> tripsList) {
        for (int j = 0; j < customerIds.size(); j++) {
            for (int i = 0; i < stations.size(); i++) {
                if (stations.get(i).substring(0, 1).equals(customerIds.get(j).toString())) {
                    stationsOfCustomer.add(stations.get(i).substring(2, 3));
                    unixTimestampOfCustomer.add(Long.valueOf(stations.get(i).substring(4)));
                }
            }

            unixTimestampOfCustomer = createTrips(stationsOfCustomer, unixTimestampOfCustomer, tripsList, zoneIntersectionController);
            int cost = getCost(tripsList);

            createCustomer(listOfCustomer, customerIds, tripsList, j, cost);
            tripsList = new ArrayList<>();
            stationsOfCustomer = new ArrayList<>();
        }
    }
}
