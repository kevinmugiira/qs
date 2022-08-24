package com.example.qs;

import com.example.qs.config.TwilioConfig;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class QsApplication {


	@Autowired
	private TwilioConfig twilioConfig;

	//initializes twilio during application startup
	@PostConstruct
	private void initTwilio() {

		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(QsApplication.class, args);
	}

}
