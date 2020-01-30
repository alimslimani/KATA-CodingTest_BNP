package com.bnp.coding.controllers;

import com.bnp.coding.models.Taps;
import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20
 */

public class ReaderFileControllerImpl implements ReaderFileController {
    JSONParser jsonParser = new JSONParser();

    @Override
    public List<Taps> getTapsObjectFromInputFile(String filePath) {
        List<Taps> tapsList = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            //Read JSON file
            Object tapsObject = jsonParser.parse(reader);
            Gson gson = new Gson();
            String jsonFromPojo = gson.toJson(tapsObject);
            ModelTapsObject object = new Gson().fromJson(jsonFromPojo, ModelTapsObject.class);
            for (Taps mi : object.taps) {
                tapsList.add(mi);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tapsList;
    }
}

class ModelTapsObject {
    List<Taps> taps;
}
