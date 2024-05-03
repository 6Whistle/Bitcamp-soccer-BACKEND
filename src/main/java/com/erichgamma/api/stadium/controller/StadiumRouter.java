package com.erichgamma.api.stadium.controller;

import com.erichgamma.api.stadium.repository.StadiumDAOImpl;
import com.erichgamma.api.stadium.repository.StadiumRepository;
import com.erichgamma.api.stadium.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;


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
            case "find" -> stadiumRepository.getAllStadium();
            default -> List.of();
        };
    }
}
