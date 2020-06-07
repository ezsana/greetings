package com.codecool.zsana.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DbCommunicationTest {

    private DbCommunication dbCommunication;

    @BeforeEach
    void setUp() {
        dbCommunication = new DbCommunication();
    }

    @Test
    void smokeTest() {
        Assertions.assertNotNull(dbCommunication);
    }

    @Test
    void dbConnectionIsWorkingTest() {
        Connection connection = Connect.connect();
        Assertions.assertNotNull(connection);
        connection = Connect.closeConnection(connection);
        Assertions.assertNull(connection);
    }

    @Test
    void getData() throws Exception {
        List<Map<String,String>> friends = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("lastName", "Huxley");
        map.put("firstName", "Carol");
        map.put("dateOfBirth", "1988/12/12");
        map.put("email", "carol.huxley@foobar.com");
        friends.add(map);
        Assertions.assertTrue(GreetingTest.areMapListsEqual(friends, dbCommunication.getData("firstName", "Carol")));
    }

    @Test
    void getFriendsByDateInterval() {
        List<Map<String,String>> friends = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        map1.put("lastName", "Doe");
        map1.put("firstName", "John");
        map1.put("dateOfBirth", "1982/10/08");
        map1.put("email", "john.doe@foobar.com");
        friends.add(map1);
        map2.put("lastName", "Huxley");
        map2.put("firstName", "Carol");
        map2.put("dateOfBirth", "1988/12/12");
        map2.put("email", "carol.huxley@foobar.com");
        friends.add(map2);
        Assertions.assertTrue(GreetingTest.areMapListsEqual(friends, dbCommunication.getFriendsByDateInterval("1982/10/01", "1989/01/01")));
    }
}