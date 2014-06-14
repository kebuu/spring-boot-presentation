package com.kebuu.springboot.server.controller;

import com.kebuu.springboot.server.config.StepConfig;
import com.kebuu.springboot.server.domain.GameStep;
import com.kebuu.springboot.server.enums.Step;
import com.kebuu.springboot.server.pojo.GameStatus;
import com.kebuu.springboot.server.repository.GameStepRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class StepController {

    @Autowired
    private StepConfig stepConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GameStepRepository gameStepRepository;
    @Autowired
    private Environment environment;

    @RequestMapping("/{userPseudo}/start")
    public ResponseEntity<Void> startPlaying(String userPseudo) {
        GameStep gameStep = new GameStep(userPseudo, Step._0);
        gameStepRepository.save(gameStep);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/{userPseudo}/validateStep1")
    public ResponseEntity<Void> validateStep1(String userPseudo, @RequestParam("userHostAndPort") String userHostAndPort) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(userHostAndPort + "/health", String.class);
        Optional.of(responseEntity)
            .filter(x -> x.getStatusCode() == HttpStatus.OK)
            .ifPresent(x -> {
                gameStepRepository.save(new GameStep(userPseudo, Step._1));
            });

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{userPseudo}/validateStep2", method = RequestMethod.POST)
    public ResponseEntity<Void> validateStep2(String userPseudo, @RequestParam("secret") String secret) {
        Optional.of(stepConfig.getStep2())
            .filter(x -> x.equals(secret))
            .ifPresent(x -> {
                gameStepRepository.save(new GameStep(userPseudo, Step._2));
            });
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{userPseudo}/validateStep3", method = RequestMethod.POST)
    public ResponseEntity<Void> validateStep3(String userPseudo, @RequestParam("secret") String secret) {
        Optional.of(stepConfig.getStep3())
            .filter(x -> x.equals(secret))
            .ifPresent(x -> {
                gameStepRepository.save(new GameStep(userPseudo, Step._3));
            });
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/{userPseudo}/validateStep4")
    public ResponseEntity<Void> validateStep4(String userPseudo, @RequestParam("userHostAndPort") String userHostAndPort) {
        String url = userHostAndPort + "/env";

        ResponseEntity<String> responseEntityShouldFail = restTemplate.getForEntity(url, String.class);

        byte[] encodedAuth = Base64.encodeBase64("zenika:technnozaure".getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntityShouldSucceed = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if(responseEntityShouldFail.getStatusCode() == HttpStatus.FORBIDDEN &&
                   responseEntityShouldSucceed.getStatusCode() == HttpStatus.OK) {
                    gameStepRepository.save(new GameStep(userPseudo, Step._4));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/{userPseudo}/validateStep5")
    public ResponseEntity<Void> validateStep5(String userPseudo) {
        //Trouver le hash du git.commit
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/{userPseudo}/validateStep6")
    public ResponseEntity<Void> validateStep6(String userPseudo) {
        //Ajouter un filter CORS
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/gameStatus")
    public ResponseEntity<GameStatus> gameStatus() {
        Iterable<GameStep> gameSteps = gameStepRepository.findAll();

        StreamSupport.stream(gameSteps.spliterator(), false);

        return new ResponseEntity<GameStatus>(HttpStatus.OK);
    }
}
