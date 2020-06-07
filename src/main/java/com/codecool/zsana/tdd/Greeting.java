package com.codecool.zsana.tdd;

import java.io.IOException;
import java.util.*;

public class Greeting {

    private Scanner myObj = new Scanner(System.in);
    private DbCommunication comm = new DbCommunication();

    String greetingMessage(String firstName) {
        return "Subject: Happy birthday!\n" +
                "\n" +
                " Happy birthday, dear " + firstName + "!";
    }

    List<Map<String, String>> getFriendDetails(String from, String to) {
        return comm.getFriendsByDateInterval(from, to);
    }

    private String askForInput() {
        System.out.println("Please enter date interval: from-to: yyyy/mm/dd-yyyy/mm/dd. " +
                "If you're looking for a day only, please " +
                "use the same date twice.");
        return myObj.nextLine();
    }

    public static void main(String[] args) throws IOException {
        Greeting greeting = new Greeting();
        String[] input = greeting.askForInput().split("-");
        List<Map<String, String>> friendDetails = greeting.getFriendDetails(input[0], input[1]);
        for (int i = 0; i < friendDetails.size(); i++) {
            String message = greeting.greetingMessage(friendDetails.get(i).get("firstName"));
            System.out.println(message);
        }
    }

}
