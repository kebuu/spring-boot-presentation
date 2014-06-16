package com.kebuu.springboot.server.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserGameStatus {

    private String pseudo;
    private Collection<StepInfo> stepInfos = new ArrayList<>();

}
