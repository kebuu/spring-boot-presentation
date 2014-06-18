package com.kebuu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Step2Helper {

    @Autowired
    private JdbcTemplate template;

    @PostConstruct
    public void getStep2Secret() {
        System.out.println(template.queryForObject("SELECT * from spring_boot_step_2", String.class));
    }
}
