package com.erichgamma.api.stadium.controller;

import com.erichgamma.api.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class StadiumRouter {

    private final StadiumRepository stadiumRepository;

    public List<?> execute(String q) {
        return switch (q) {
            case "11" -> stadiumRepository.stadiumNameWithTeam();
            case "14" -> stadiumRepository.stadiumAndTeamAndSchedule();
            case "15" -> stadiumRepository.pohangSteelersGk();
            case "16" -> stadiumRepository.homeTeamWin();
            case "17" -> stadiumRepository.noHomeTeam();
            case "DSL1" -> stadiumRepository.getAllStadiumsDSL();
            case "DSL2" -> stadiumRepository.stadiumNameWithTeamDSL();
            case "DSL3" -> stadiumRepository.stadiumAndTeamAndScheduleDSL();
            case "DSL4" -> stadiumRepository.pohangSteelersGkDSL();
            case "DSL5" -> stadiumRepository.homeTeamWinDSL();
            case "DSL6" -> stadiumRepository.noHomeTeamDSL();
            default -> List.of();
        };
    }

    public Long countAllStadiums() {
        return stadiumRepository.countAllStadiums() ;
    }

}

