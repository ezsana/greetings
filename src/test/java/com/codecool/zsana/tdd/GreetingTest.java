package com.codecool.zsana.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GreetingTest {

    private Greeting greeting;
    private String filePath;
    @BeforeEach
    void setUp() {
        this.greeting = new Greeting();
        filePath = "friends.csv";
    }

    @Test
    void smokeTest() {
        Assertions.assertNotNull(greeting);
    }

    @Test
    void greetingMessageTest() {
        String firstName = "Allen";
        String message = "Subject: Happy birthday!\n" +
                "\n" +
                " Happy birthday, dear Allen!";
        Assertions.assertEquals(message, greeting.greetingMessage(firstName));
    }

    @Test
    void csvReaderTest() throws IOException {
        List<Map<String, String>> details = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("lastName", "Doe");
        map.put("firstName", "John");
        map.put("dateOfBirth", "1982/10/08");
        map.put("email", "john.doe@foobar.com");
        details.add(map);
        Assertions.assertTrue(areMapListsEqual(details, greeting.getFriendDetails("1982/10/08", filePath)));
    }

    private boolean areMapListsEqual(List<Map<String, String>> listMap1, List<Map<String, String>> listMap2) {
        if (listMap1.size() != listMap2.size()) {
            return false;
        }
        for (int i = 0; i < listMap1.size(); i++) {
            if (listMap1.get(i).size() != listMap2.get(i).size()) {
                return false;
            } else {
                for (String key : listMap1.get(i).keySet()) {
                    String value1 = listMap1.get(i).get(key);
                    String value2 = listMap2.get(i).get(key);
                    if (!value1.equals(value2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}