package com.kebuu.springboot.server.repository;

import com.kebuu.springboot.server.domain.GameStep;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameStepRepository extends PagingAndSortingRepository<GameStep, Long> {

}