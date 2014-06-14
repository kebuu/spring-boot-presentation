package com.kebuu.springboot.server.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.Collection;

@Data
public class GameStatus {

    private Collection<UserGameStatus> userGameStatus = Arrays.asList();
}
