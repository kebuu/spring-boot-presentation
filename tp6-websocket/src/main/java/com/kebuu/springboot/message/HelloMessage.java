package com.kebuu.springboot.message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HelloMessage {

    private String name;

    public String getName() {
        return name;
    }

}