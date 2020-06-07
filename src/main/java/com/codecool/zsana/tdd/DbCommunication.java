package com.codecool.zsana.tdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbCommunication {

    List<Map<String, String>> getData(String column, String data) throws Exception {
        String query;
        if (column.equals("firstName")) {
            query = "SELECT * FROM friends WHERE firstName like ?;";
        } else if (column.equals("lastName")) {
            query = "SELECT * FROM friends WHERE lastName like ?;";
        } else if (column.equals("dateOfBirth")) {
            query = "SELECT * FROM friends WHERE dateOfBirth like ?;";
        } else if (column.equals("email")) {
            query = "SELECT * FROM friends WHERE email like ?;";
        } else {
            throw new Exception("Data is not valid");
        }
        List<Map<String, String>> friendData = new ArrayList<Map<String, String>>();
        Connection connection = Connect.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, data);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("lastName", set.getString(1));
                map.put("firstName", set.getString(2));
                map.put("dateOfBirth", set.getString(3));
                map.put("email", set.getString(4));
                friendData.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(connection);
        }
        return friendData;
    }

    List<Map<String, String>> getFriendsByDateInterval(String from, String to) {
        String query = "SELECT * FROM friends WHERE dateOfBirth BETWEEN ? AND ?;";
        return getDataFromDb(from, to, query);
    }

    List<Map<String, String>> getDataFromDb(String s1, String s2, String query) {
        List<Map<String, String>> friendData = new ArrayList<Map<String, String>>();
        Connection connection = Connect.connect();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, s1);
            ps.setString(2, s2);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("lastName", set.getString(1));
                map.put("firstName", set.getString(2));
                map.put("dateOfBirth", set.getString(3));
                map.put("email", set.getString(4));
                friendData.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(connection);
        }
        return friendData;
    }


}
