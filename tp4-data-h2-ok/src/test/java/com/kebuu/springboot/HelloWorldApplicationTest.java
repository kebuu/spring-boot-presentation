package com.kebuu.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kebuu.springboot.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringDataH2Application.class)
@WebAppConfiguration
@IntegrationTest
public class HelloWorldApplicationTest {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testPostPerson() throws JsonProcessingException {
        Person person = new Person();
        person.setFirstName("Christophe");
        person.setLastName("TARDELLA");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> postPersonEntity = new HttpEntity<String>(objectMapper.writeValueAsString(person), headers);
        ResponseEntity<String> httpEntity = restTemplate.postForEntity("http://localhost:8050/people", postPersonEntity, String.class);
        assertThat(httpEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(restTemplate.getForEntity("http://localhost:8050/people/1", Person.class).getBody()).isEqualTo(person);
    }
}
