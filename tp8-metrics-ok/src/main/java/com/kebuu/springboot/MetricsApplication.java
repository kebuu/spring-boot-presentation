package com.kebuu.springboot;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class MetricsApplication {

    public static final String ZEN_MEASURE_COUNTED = "zen.measure.counted";

    private final CounterService counterService;

    @Autowired
    public MetricsApplication(CounterService counterService, MetricRegistry metricRegistry) {
        this.counterService = counterService;

        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
    }

    @RequestMapping("/measure")
    String measure() {
        counterService.increment(ZEN_MEASURE_COUNTED);
        return "Done";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MetricsApplication.class, args);
    }

}
