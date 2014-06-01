package com.kebuu.springboot.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/")
    public String home() {
        return "Not secured";
    }

    @RequestMapping("/secured")
    //TODO A décommenter en temps utile @Secured("ROLE_USER_ZENIKA")
    public String secured() {
        return "Secured but you got me !";
    }
}
