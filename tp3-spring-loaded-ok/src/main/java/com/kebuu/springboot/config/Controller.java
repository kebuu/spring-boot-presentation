package com.kebuu.springboot.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private ReloadedBean reloadedBean;

    @RequestMapping("/")
    public String home() {
        log.info("Reloaded code OK !");
        return "Sweet home !";
    }

    @RequestMapping("/reloaded")
    public String reloaded() {
        return "Reload request mapping OK !";
    }

    @RequestMapping("/reloaded/class")
    public ReloadedClass reloadedClass() {
        return new ReloadedClass();
    }

    @RequestMapping("/reloaded/bean")
    public String reloadedBean() {
        if(reloadedBean == null) {
            return "Reload bean FAILED !";
        } else {
            return reloadedBean.getMessage();
        }
    }

    @Data
    public static class ReloadedClass {
        private String message = "Reload class OK";
    }
}
