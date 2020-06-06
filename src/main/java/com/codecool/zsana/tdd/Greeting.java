package com.codecool.zsana.tdd;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Greeting {

    String greetingMessage(String firstName) {
        return "Subject: Happy birthday!\n" +
                "\n" +
                " Happy birthday, dear " + firstName + "!";
    }

    List<Map<String, String>> getFriendDetails(String date, String filePath) throws IOException {
        List<String[]> allData = csvReader(filePath);
        String[] keys = allData.get(0);
        List<Map<String, String>> friendDetails = new ArrayList<Map<String, String>>();

        for (int i = 1; i < allData.size(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            if (allData.get(i)[2].equals(date)) {
                map.put(keys[0], allData.get(i)[0]);
                map.put(keys[1], allData.get(i)[1]);
                map.put(keys[2], allData.get(i)[2]);
                map.put(keys[3], allData.get(i)[3]);
                friendDetails.add(map);
            }
        }
        return friendDetails;
    }

    List<String[]> csvReader(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath), ',', '"', 0);
        return reader.readAll();
    }

}
