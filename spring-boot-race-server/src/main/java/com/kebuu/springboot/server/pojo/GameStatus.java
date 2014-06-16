package com.kebuu.springboot.server.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class GameStatus {

    private Collection<UserGameStatus> userGameStatus = new ArrayList<>();
}
