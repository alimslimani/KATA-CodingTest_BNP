package com.bnp.coding;

import com.bnp.coding.controllers.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20s
 * ls
 */

public class CodingTrainCompany {

    public static void main(String[] args) throws IOException {
//      main method take 2 input parameters
//      the input file path
        String filePathReader = args[0];
//      the second is the output file path
        String filePathWriter = args[1];
//      init all controllers
        ReaderFileController readerFileController = new ReaderFileControllerImpl();
        ZoneIntersectionController zoneIntersectionController = new ZoneIntersectionControllerImpl();
        CustomerController customerController = new CustomerControllerImpl();
//      Treatment
        String finalResult = customerController.getListOfCustomersFromInputFile(filePathReader, readerFileController, zoneIntersectionController, customerController);
//      Write result like json format
        try (FileWriter file = new FileWriter(filePathWriter)) {
            file.write(finalResult);
            System.out.println("Successfully Copied JSON Object to File...");
        }
    }
}



