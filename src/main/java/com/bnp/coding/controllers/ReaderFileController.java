package com.bnp.coding.controllers;

import com.bnp.coding.models.Taps;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20
 */

public interface ReaderFileController {
    List<Taps> getTapsObjectFromInputFile(String filePath);
}
