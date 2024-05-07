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
            case "findAllDSL" -> stadiumRepository.getAllStadiumsDSL();
            case "test" -> stadiumRepository.stadiumNameWithTeamDSL();
            default -> List.of();
        };
    }
}
