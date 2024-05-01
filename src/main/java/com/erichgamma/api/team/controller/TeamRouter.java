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

    public List<Map<String, Object>> execute(String q){
        return switch (q) {
            case "1" -> teamRepository.getTeamsOrderByTeamName();
            case "12" -> teamRepository.getTeamsByteamName().stream()
                                        .filter(i -> ((String)(i.get("teamName"))).equals("K02") || ((String)(i.get("teamName"))).equals("K10"))
                                        .filter(i -> (Integer)(i.get("height")) >= 180 && (Integer)(i.get("height")) <= 183)
                                        .toList();
            case "13" -> teamRepository.getNotSelectedPostion();
            case "19" -> teamRepository.getHeightAvgByTeam().stream()
                                        .filter(i -> (Double)(i.get("평균")) < 176.59)
                                        .toList();
            default -> List.of();
        };
    }
}
