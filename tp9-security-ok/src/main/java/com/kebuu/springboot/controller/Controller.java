package com.kebuu.springboot.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/")
    public String home() {
        return "Not secured";
    }

    @RequestMapping("/secured")
    @Secured("ROLE_USER_ZENIKA")
    public String secured() {
        return "Secured but you got me !";
    }
}
