package com.bnp.coding;

import com.bnp.coding.controllers.*;
import com.bnp.coding.models.Taps;
import com.bnp.coding.models.Trips;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CodingTrainCompanyTest {

    ReaderFileController readerFileController = new ReaderFileControllerImpl();
    ZoneIntersectionController zoneIntersectionController = new ZoneIntersectionControllerImpl();
    CustomerController customerController = new CustomerControllerImpl();
    List<Taps> tapsList = new ArrayList<>();
    List<String> stations = new ArrayList<>();
    List<String> stationsOfCustomer = new ArrayList<>();
    List<Long> unixTimestampOfCustomer = new ArrayList<>();
    List<Integer> customerIds = new ArrayList<>();
    List<Trips> tripsList = new ArrayList<>();
    JSONArray listOfCustomer = new JSONArray();

    Taps t1 = new Taps(1, 1, "A");
    Taps t2 = new Taps(2, 1, "B");
    Taps t3 = new Taps(2, 1, "D");
    Taps t4 = new Taps(2, 1, "F");
    Taps t5 = new Taps(2, 1, "H");
    Taps t6 = new Taps(1, 1, "C");
    Taps t7 = new Taps(2, 1, "E");

    @Test
    public void testTravelWithinZ1To1() {
        tapsList.add(t1);
        tapsList.add(t2);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 240);
        assertEquals(1, tripsList.get(0).getZoneFrom());
        assertEquals(1, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ1To2() {
        tapsList.add(t1);
        tapsList.add(t3);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 240);
        assertEquals(1, tripsList.get(0).getZoneFrom());
        assertEquals(2, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ2To1() {
        tapsList.add(t3);
        tapsList.add(t1);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 240);
        assertEquals(2, tripsList.get(0).getZoneFrom());
        assertEquals(1, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ4To4() {
        tapsList.add(t4);
        tapsList.add(t5);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 200);
        assertEquals(4, tripsList.get(0).getZoneFrom());
        assertEquals(4, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ1To4() {
        tapsList.add(t1);
        tapsList.add(t5);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 300);
        assertEquals(1, tripsList.get(0).getZoneFrom());
        assertEquals(4, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ1Or2To4() {
        tapsList.add(t3);
        tapsList.add(t5);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 300);
        assertEquals(2, tripsList.get(0).getZoneFrom());
        assertEquals(4, tripsList.get(0).getZoneTo());
        if (tapsList.contains(t3)) {
            tapsList.clear();
            tripsList.clear();
            stations.clear();
            customerIds.clear();
            listOfCustomer.remove(0);
            tapsList.add(t1);
            tapsList.add(t5);
            customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
            customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
            assertEquals(tripsList.get(0).getCostInCents(), 300);
            assertEquals(1, tripsList.get(1).getZoneFrom());
            assertEquals(4, tripsList.get(1).getZoneTo());
        }
    }

    @Test
    public void testTravelWithinZ2To2() {
        tapsList.add(t3);
        tapsList.add(t6);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 240);
        assertEquals(2, tripsList.get(0).getZoneFrom());
        assertEquals(2, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ3To3() {
        tapsList.add(t6);
        tapsList.add(t4);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 200);
        assertEquals(3, tripsList.get(0).getZoneFrom());
        assertEquals(3, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testTravelWithinZ4To1Or2() {
        tapsList.add(t5);
        tapsList.add(t1);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 300);
        assertEquals(4, tripsList.get(0).getZoneFrom());
        assertEquals(1, tripsList.get(0).getZoneTo());
        if (tapsList.contains(t1)) {
            tapsList.clear();
            tripsList.clear();
            stations.clear();
            customerIds.clear();
            listOfCustomer.remove(0);
            tapsList.add(t5);
            tapsList.add(t3);
            customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
            customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
            assertEquals(tripsList.get(0).getCostInCents(), 300);
            assertEquals(4, tripsList.get(1).getZoneFrom());
            assertEquals(2, tripsList.get(1).getZoneTo());
        }
    }

    @Test
    public void testTravelWithinZ3To4() {
        tapsList.add(t7);
        tapsList.add(t5);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 200);
        assertEquals(3, tripsList.get(0).getZoneFrom());
        assertEquals(4, tripsList.get(0).getZoneTo());
    }

    @Test
    public void testLimitTravelCostTo500() {
        tapsList.add(t1);
        tapsList.add(t3);
        tapsList.add(t4);
        tapsList.add(t5);
        tapsList.add(t6);
        tapsList.add(t7);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        Gson gsonConverter = new Gson();
        String result = gsonConverter.toJson(listOfCustomer);
        assertEquals(result.contains("\"totalCostInCents\":500"), true);
    }

    @Test
    public void testTravelWithinZ3To1Or2() {
        tapsList.add(t4);
        tapsList.add(t1);
        customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
        customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
        assertEquals(tripsList.get(0).getCostInCents(), 280);
        assertEquals(3, tripsList.get(0).getZoneFrom());
        assertEquals(1, tripsList.get(0).getZoneTo());
        if (tapsList.contains(t1)) {
            tapsList.clear();
            tripsList.clear();
            stations.clear();
            customerIds.clear();
            listOfCustomer.remove(0);
            tapsList.add(t4);
            tapsList.add(t3);
            customerController.getCustomerIdAndStationsAndTime(tapsList, stations, customerIds);
            customerController.createTripsAndCustomers(zoneIntersectionController, listOfCustomer, stations, stationsOfCustomer, unixTimestampOfCustomer, customerIds, tripsList);
            assertEquals(tripsList.get(0).getCostInCents(), 280);
            assertEquals(3, tripsList.get(1).getZoneFrom());
            assertEquals(2, tripsList.get(1).getZoneTo());
        }
    }

    @Test
    public void testTravelWithinDifferentZones() throws IOException {
        String filePathWriter = "src\\test\\java\\com\\bnp\\coding\\output1.json";
        String filePathReader = "src\\test\\java\\com\\bnp\\coding\\CandidateInputExample.txt";
        File output = new File(filePathWriter);
        File outputResult = new File(filePathWriter);

        String finalResult = customerController.getListOfCustomersFromInputFile(filePathReader, readerFileController, zoneIntersectionController, customerController);

        try (FileWriter file = new FileWriter(filePathWriter)) {
            file.write(finalResult);
        }

        assertTrue("The result is like the candidateoutput!", FileUtils.contentEquals(output, outputResult));
    }

}
