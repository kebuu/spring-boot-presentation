package com.kebuu.springboot.server.controller;

import com.kebuu.springboot.server.config.StepConfig;
import com.kebuu.springboot.server.domain.GameStep;
import com.kebuu.springboot.server.enums.Step;
import com.kebuu.springboot.server.pojo.GameStatus;
import com.kebuu.springboot.server.pojo.StepInfo;
import com.kebuu.springboot.server.pojo.UserGameStatus;
import com.kebuu.springboot.server.repository.GameStepRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
public class StepController {

    @Autowired
    private StepConfig stepConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GameStepRepository gameStepRepository;
    @Autowired
    private Environment environment;
    @Autowired
    private SimpMessagingTemplate webSocketTemplate;

    @RequestMapping("/{userPseudo}/start")
    public ResponseEntity<Void> startPlaying(@PathVariable("userPseudo") String userPseudo) {
        log.info("Starting game for player {}", userPseudo);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping("/{userPseudo}/validateStep1")
    public ResponseEntity<Void> validateStep1(@PathVariable("userPseudo") String userPseudo, @RequestParam("userHostAndPort") String userHostAndPort) {
        log.info("Validation step 1 for player {} with data {}", userPseudo, userHostAndPort);
        ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://" + userHostAndPort + "/health", String.class);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            gameStepRepository.save(new GameStep(userPseudo, Step._1));
            result = new ResponseEntity<Void>(HttpStatus.OK);
            broadcastGameStatus();
        }

        broadcastGameStatus();
        return result;
    }

    @RequestMapping("/{userPseudo}/validateStep2")
    public ResponseEntity<Void> validateStep2(@PathVariable("userPseudo") String userPseudo, @RequestParam("secret") String secret) {
        log.info("Validation step 2 for player {} with data {}", userPseudo, secret);

        ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        if(stepConfig.getStep2().equals(secret)) {
            gameStepRepository.save(new GameStep(userPseudo, Step._2));
            result = new ResponseEntity<Void>(HttpStatus.OK);
            broadcastGameStatus();
        }

        return result;
    }

    @RequestMapping("/{userPseudo}/validateStep3")
    public ResponseEntity<Void> validateStep3(@PathVariable("userPseudo") String userPseudo, @RequestParam("secret") String secret) {
        log.info("Validation step 3 for player {} with data {}", userPseudo, secret);

        ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        if(stepConfig.getStep3().equals(secret)) {
            gameStepRepository.save(new GameStep(userPseudo, Step._3));
            result = new ResponseEntity<Void>(HttpStatus.OK);
            broadcastGameStatus();
        }

        return result;
    }

    @RequestMapping("/{userPseudo}/validateStep4")
    public ResponseEntity<Void> validateStep4(@PathVariable("userPseudo") String userPseudo, @RequestParam("userHostAndPort") String userHostAndPort) {
        log.info("Validation step 4 for player {} with data {}", userPseudo, userHostAndPort);

        ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        String url = "http://" + userHostAndPort + "/env";

        ResponseEntity<String> responseEntityShouldFail = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);

        byte[] encodedAuth = Base64.encodeBase64("zenika:technozaure".getBytes());
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntityShouldSucceed = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if(responseEntityShouldFail.getStatusCode() != HttpStatus.OK && responseEntityShouldSucceed.getStatusCode() == HttpStatus.OK) {
            gameStepRepository.save(new GameStep(userPseudo, Step._4));
            result = new ResponseEntity<Void>(HttpStatus.OK);
            broadcastGameStatus();
        }

        return result;
    }

    @RequestMapping("/{userPseudo}/validateStep5")
    public ResponseEntity<Void> validateStep5(@PathVariable("userPseudo") String userPseudo, @RequestParam("secret") String secret) {
        log.info("Validation step 5 for player {} with data {}", userPseudo, secret);

        ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        if(environment.getProperty("git.commit.id.abbrev").equals(secret)) {
            gameStepRepository.save(new GameStep(userPseudo, Step._5));
            result = new ResponseEntity<Void>(HttpStatus.OK);
            broadcastGameStatus();
        }

        return result;
    }

    @RequestMapping("/{userPseudo}/validateStep6")
    public ResponseEntity<Void> validateStep6(@PathVariable("userPseudo")String userPseudo, @RequestParam("userHostAndPort") String userHostAndPort) {
        log.info("Validation step 6 for player {} with data {}", userPseudo, userHostAndPort);
        ResponseEntity<Void> result = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        //Ajouter un filter CORS
        return result;
    }

    @RequestMapping("/gameStatus")
    public ResponseEntity<GameStatus> broadcastGameStatus() {
        GameStatus gameStatus = new GameStatus();

        Iterable<GameStep> gameSteps = gameStepRepository.findAll();

        List<GameStep> collect = StreamSupport.stream(gameSteps.spliterator(), false).collect(toList());

        Map<String, List<GameStep>> gameStepByUser = StreamSupport.stream(gameSteps.spliterator(), false)
                                                             .collect(Collectors.groupingBy(GameStep::getUserPseudo));

        for (Map.Entry<String, List<GameStep>> gameStepByUserEntry : gameStepByUser.entrySet()) {
            UserGameStatus userGameStatus = new UserGameStatus();

            userGameStatus.setPseudo(gameStepByUserEntry.getKey());

            Map<Step, List<GameStep>> gameStepByStep = gameStepByUserEntry.getValue().stream().collect(Collectors.groupingBy(GameStep::getStep));

            for (Map.Entry<Step, List<GameStep>> gameStepByStepEntry : gameStepByStep.entrySet()) {
                gameStepByStepEntry.getValue().stream()
                        .min(Comparator.comparing(GameStep::getInstant))
                        .ifPresent(gameStep -> {
                            userGameStatus.getStepInfos().add(new StepInfo(gameStep.getStep(), gameStep.getInstant()));

                        });
            }

            gameStatus.getUserGameStatus().add(userGameStatus);
        }

        webSocketTemplate.convertAndSend("/topic/gameStatus", gameStatus);
        return new ResponseEntity<GameStatus>(gameStatus, HttpStatus.OK);
    }
}
