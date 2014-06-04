package com.kebuu.springboot;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MetricsApplication.class)
@WebAppConfiguration
@IntegrationTest
public class MetricsApplicationTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testHealth() {
        assertThat(restTemplate.getForObject("http://localhost:8051/zen/health", String.class)).isEqualTo("ok");
    }

    @Test
    public void testCustomMetrics() {
        String measureUrl = "http://localhost:8050/measure";
        restTemplate.getForObject(measureUrl, String.class);
        restTemplate.getForObject(measureUrl, String.class);
        ObjectNode responseNode = restTemplate.getForObject("http://localhost:8051/zen/metrics", ObjectNode.class);

        assertThat(responseNode.path("counter." + MetricsApplication.ZEN_MEASURE_COUNTED).asInt()).isEqualTo(2);
    }
}
