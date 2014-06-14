package com.kebuu.springboot.server.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.Collection;

@Data
public class UserGameStatus {

    private String pseudo;
    private Collection<StepInfo> stepInfos = Arrays.asList();

}
