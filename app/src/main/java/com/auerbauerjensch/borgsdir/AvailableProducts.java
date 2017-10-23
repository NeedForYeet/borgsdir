package com.auerbauerjensch.borgsdir;

import java.util.Random;

/**
 * Created by user on 20.01.2017.
 */

public class AvailableProducts {

    private static final Random RANDOM = new Random();

    public static final String[] productNames = {
            "Fahrrad wie neu", "Fahrrad für Damen zu verborgen", "Verborge Fahrrad", "Kettensäge zum Herborgen", "Stihl Kettensäge zu verleihen", "Kettensäge mit Helm",
            "Akkuschrauber, wie neu zum Ausborgen", "Borge Bohrmaschine", "Kabelgebundene Bohrmaschine",
            "Heißluftföhn 2500 Watt", "Fast neuer Heißluftföhn zu verborgen",
            "Verborge Rasenmäher", "Rasenmäher mit großem Fangkorb",
            "Multimeter zu verborgen", "Multimeter mit viel Zubehör",
            "Verborge Kärcher Hochdruckreiniger",
            "Kreissäge mit Sägeblättern", "Kreissäge mit neuem Sägeblatt", "Kreissäge für zu verborgen",
            "Borge Werkzeugkasten mit viel Zubehör",
            "Industrie-Staubsauger mit hoher Saugleistung",
            "Wärmebildkamera mit Zubehör"
    };

    public static final String[] productLocation = {"9220, Velden am Wörthersee", "9020, Klagenfurt", "9064, Völkermarkt", "9500, Villach", "9400, Wolfsberg"};

    public static String getRandomProductLocation() {
        return productLocation[RANDOM.nextInt(productLocation.length)];
    }

    public static int getRandomProductLogo(String productName) {

        if (productName.contains("Fahrrad")) {
            return R.drawable.bike_stock_1;
        } else if (productName.contains("Kettensäge")) {
            return R.drawable.chainsaw_stock_1;
        } else if (productName.contains("Hochdruckreiniger")) {
            return R.drawable.pressure_stock_1;
        } else if (productName.contains("Akkuschrauber") || productName.contains("Bohrmaschine")) {
            return R.drawable.drill_stock_1;
        } else if (productName.contains("Heißluft")) {
            return R.drawable.heatgun_stock_1;
        } else if (productName.contains("Rasenmäher")) {
            return R.drawable.lawnmower_stock_1;
        } else if (productName.contains("Multimeter")) {
            return R.drawable.multimeter_stock_1;
        } else if (productName.contains("Kreissäge")) {
            return R.drawable.saw_stock_1;
        } else if (productName.contains("Staubsauger")) {
            return R.drawable.vacuum_stock_1;
        } else if (productName.contains("Werkzeug")) {
            return R.drawable.tools_stock_1;
        } else if (productName.contains("Wärmebild")) {
            return R.drawable.infrared_stock_1;
        }
        return R.drawable.noimage;
    }
}
