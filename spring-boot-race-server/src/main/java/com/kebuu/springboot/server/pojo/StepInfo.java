package com.kebuu.springboot.server.pojo;

import com.kebuu.springboot.server.enums.Step;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class StepInfo {

    private Step step;
    private Instant instant;
}
