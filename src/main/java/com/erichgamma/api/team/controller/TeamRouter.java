package com.erichgamma.api.team.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.erichgamma.api.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TeamRouter {
    private final TeamRepository teamRepository;

    public List<?> execute(String q){
        return switch (q) {
            case "1" -> teamRepository.getTeamsOrderByTeamName();
            case "12" -> ((List<Map<String, Object>>)teamRepository.getTeamsByteamName()).stream()
                                        .filter(i -> ((String)(i.get("teamId"))).equals("K02") || ((String)(i.get("teamId"))).equals("K10"))
                                        .filter(i -> Integer.parseInt((String)(i.get("height"))) >= 180 && Integer.parseInt((String)(i.get("height"))) <= 183)
                                        .toList();
            case "13" -> teamRepository.getNotSelectedPostion();
            case "19" -> ((List<Map<String, Object>>)teamRepository.getHeightAvgByTeam()).stream()
                                        .filter(i -> (Double)(i.get("평균")) < 176.59)
                                        .toList();
            case "findAll" -> teamRepository.getAllTeams();
            default -> List.of();
        };
    }
}
