package com.kebuu.springboot;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@EnableAutoConfiguration
public class MetricsApplication {

    public static final String ZEN_MEASURE_COUNTED = "zen.measure.counted";
    public static final String ZEN_MEASURE_TIMED = "histogram.zen.measure.timed";
    public static final Random random = new Random();

    private final CounterService counterService;
    private final GaugeService gaugeService;
    private final MetricRegistry metricRegistry;

    @Autowired
    public MetricsApplication(CounterService counterService, GaugeService gaugeService, MetricRegistry metricRegistry) {
        this.counterService = counterService;
        this.gaugeService = gaugeService;
        this.metricRegistry = metricRegistry;

        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
        random.setSeed(1);
    }

    @RequestMapping("/measure")
    String timerAndCounter() {
        int randomValue = random.nextInt(100);
        gaugeService.submit(ZEN_MEASURE_TIMED, randomValue);
        counterService.increment(ZEN_MEASURE_COUNTED);

        return "Done " + randomValue;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MetricsApplication.class, args);
    }

}
