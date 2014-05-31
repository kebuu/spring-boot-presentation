package com.kebuu.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=HelloWolrdApplication.class)
@WebAppConfiguration
@IntegrationTest
public class HelloWolrdApplicationTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testHello() {
        assertThat(restTemplate.getForObject("http://localhost:8050", String.class)).isEqualTo(HelloWolrdApplication.HELLO_MESSAGE);
    }
}
