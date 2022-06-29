package com.example.qs.twilio;


import org.springframework.context.annotation.Configuration;
import com.twilio.Twilio;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class TwilioInitializer {

    private final Twilioproperties twilioproperties;

    public TwilioInitializer(Twilioproperties twilioproperties) {

        this.twilioproperties = twilioproperties;
        Twilio.init(twilioproperties.getAccountId(), twilioproperties.getAuthToken());
        System.out.println("Twilio initialized with-" +twilioproperties.getAccountId());
    }
}
