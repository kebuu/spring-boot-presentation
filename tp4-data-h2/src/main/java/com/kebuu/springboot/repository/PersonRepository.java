package com.kebuu.springboot.repository;

import com.kebuu.springboot.domain.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findByLastName(String name);
}