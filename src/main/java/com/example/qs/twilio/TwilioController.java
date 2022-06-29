package com.example.qs.twilio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TwilioController {

    @RequestMapping("/")
    public String home(ModelAndView model) {

        return "Welcome to Twilio Auth";
    }
}
