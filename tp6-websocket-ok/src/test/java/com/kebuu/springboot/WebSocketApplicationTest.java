package com.kebuu.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URISyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=WebSocketApplication.class)
@WebAppConfiguration
@IntegrationTest
public class WebSocketApplicationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() throws URISyntaxException {

    }
}
