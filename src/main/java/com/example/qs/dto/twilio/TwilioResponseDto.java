package com.example.qs.dto.twilio;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TwilioResponseDto {

    private String message;
    private OtpStatus status;

    public TwilioResponseDto() {
    }

    public TwilioResponseDto(String message, OtpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OtpStatus getStatus() {
        return status;
    }

    public void setStatus(OtpStatus status) {
        this.status = status;
    }
}
