package com.example.qs.service;


import com.example.qs.config.TwilioConfig;
import com.example.qs.dto.twilio.OtpStatus;
import com.example.qs.dto.twilio.TwilioRequestDto;
import com.example.qs.dto.twilio.TwilioResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwiliOtpService {

    @Autowired
    private TwilioConfig twilioConfig;

    Map<String, String> otpMap = new HashMap<>();

    public Mono<TwilioResponseDto> sendOtp(TwilioRequestDto twilioRequestDto) {

        TwilioResponseDto twilioResponseDto = null;

        try {

            PhoneNumber to = new PhoneNumber(twilioRequestDto.getPhoneNumber()); //fetching the number to send sms to
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber()); //fetching the number to send sms from ie: twilio's trial number
            String otp = generateOtp();
            String otpMessage = "Qua-Sure OTP is: " + otp;

            Message message = Message.creator(to, from,
                            otpMessage)
                    .create();

            otpMap.put(twilioRequestDto.getUserName(), otp);  //not the recommended way of storing otp consider using an in-memory db (get it from session scope)
            twilioResponseDto = new TwilioResponseDto(otpMessage, OtpStatus.DELIVERED);

        } catch (Exception ex) {
            twilioResponseDto = new TwilioResponseDto(ex.getMessage(), OtpStatus.FAILED);
        }

        return Mono.just(twilioResponseDto);
    }

    public Mono<String> validateOtp(String userInputOtp, String userName) {
        if (userInputOtp.equals(otpMap.get(userName))) {
            return Mono.just("OTP valid !"); //use custom response
        } else {
            return Mono.error(new IllegalArgumentException("Invalid OTP, please retry !")); //use custom response
        }
    }

   //4 digit otp generation
    private String generateOtp() {
        return new DecimalFormat("0000").format(new Random(). nextInt(9999));
    }
}
