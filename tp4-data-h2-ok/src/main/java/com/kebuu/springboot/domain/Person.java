package com.kebuu.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;
}