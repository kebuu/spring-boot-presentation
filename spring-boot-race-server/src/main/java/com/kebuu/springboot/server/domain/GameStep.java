package com.kebuu.springboot.server.domain;

import com.kebuu.springboot.server.enums.Step;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userPseudo;
    private Step step;
    private Instant instant = Instant.now();

    public GameStep(String userPseudo, Step step) {
        this.userPseudo = userPseudo;
        this.step = step;
    }
}
