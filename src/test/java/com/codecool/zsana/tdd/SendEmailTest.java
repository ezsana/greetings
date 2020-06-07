package com.codecool.zsana.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

class SendEmailTest {

    @Test
    void sendEmail() throws MessagingException {
        SendEmail.sendEmail("", "Mock", "Mocking bdaymessage", "");
    }
}