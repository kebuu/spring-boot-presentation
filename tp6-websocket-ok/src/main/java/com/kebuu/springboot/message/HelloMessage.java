package com.kebuu.springboot.message;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HelloMessage {

    private String name;

    public String getName() {
        return name;
    }

}