package com.erichgamma.api.team.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.erichgamma.api.common.model.Box;
import com.erichgamma.api.common.model.PageDto;
import com.erichgamma.api.common.service.PageService;
import com.erichgamma.api.team.repository.TeamRepository;
import com.erichgamma.api.team.service.TeamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TeamRouter {
    private final TeamService teamService;
    private final PageService pageService;
    private final TeamRepository teamRepository;

    public List<?> execute(String q, Pageable pageable){

        // Box.builder()
        // .listDto(teamRepository.getAllTeamsDSL(pageable))
        // .pageDto(new PageDto(pageable.getPageSize() + 0L, 10L, pageable.getPageNumber() + 0L, teamService.count()))
        // .build();

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
            case "all" -> teamRepository.getAllTeamsDSL(pageable);
            case "no-position" -> teamRepository.getEmptyPositionDSL();
            case "player-by-height" -> teamRepository.getPlayersHavingHeightDSL().stream()
                                        .filter(i -> i.getTeamId().equals("K02") || i.getTeamId().equals("K10"))
                                        .filter(i -> Double.parseDouble(i.getHeight()) >= 180 && Double.parseDouble(i.getHeight()) <= 183)
                                        .toList();
            case "get-height-avg" -> teamRepository.getHeightAvgByTeamDSL().stream()
                                        .filter(i -> (Double)i.get("평균") < 176.59)
                                        .toList();
            default -> List.of();
        };
    }
}
