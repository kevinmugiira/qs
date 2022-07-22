package com.example.qs.dto.twilio;


import lombok.Data;

@Data
public class PasswordResetRequestDto {

    private String phoneNumber; //the phone destination for the sms
    private String userName;
    private String oneTimePassword;
}
