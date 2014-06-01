package com.kebuu.springboot.controller;

import com.kebuu.springboot.message.Greeting;
import com.kebuu.springboot.message.HelloMessage;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}