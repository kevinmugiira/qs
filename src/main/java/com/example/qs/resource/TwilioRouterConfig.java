package com.example.qs.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class TwilioRouterConfig {

    @Autowired
    private TwilioOtpHandler twilioOtpHandler;

    @Bean
    //this router acts as the controller class which then calls the handler method
    public RouterFunction<ServerResponse> handleSMS() {
        return RouterFunctions.route()
                .POST("/router/sendOTP", twilioOtpHandler::sendOTP) //api for sending the otp
                .POST("/router/validateOTP", twilioOtpHandler::validateOTP) //api for validating the otp
                .build();
    }
}
