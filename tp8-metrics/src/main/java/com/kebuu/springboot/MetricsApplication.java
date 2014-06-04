package com.kebuu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class MetricsApplication {

    public static final String ZEN_MEASURE_COUNTED = "zen.measure.counted";

    @RequestMapping("/measure")
    String measure() {
        return "Done";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MetricsApplication.class, args);
    }

}
