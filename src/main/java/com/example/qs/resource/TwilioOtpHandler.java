package com.example.qs.resource;


import com.example.qs.dto.twilio.TwilioRequestDto;
import com.example.qs.service.TwiliOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOtpHandler {

    @Autowired
    private TwiliOtpService twiliOtpService;

    public Mono<ServerResponse> sendOTP(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(TwilioRequestDto.class)
                .flatMap(twilio->twiliOtpService.sendOtp(twilio))
                .flatMap(twilio->ServerResponse.status(HttpStatus.OK)
                         .body(BodyInserters.fromValue(twilio)));

    }

    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(TwilioRequestDto.class)
                .flatMap(twilio->twiliOtpService.validateOtp(twilio.getOneTimePassword(), twilio.getUserName()))
                .flatMap(twilio->ServerResponse.status(HttpStatus.OK)
                        .bodyValue(twilio));
    }

}
