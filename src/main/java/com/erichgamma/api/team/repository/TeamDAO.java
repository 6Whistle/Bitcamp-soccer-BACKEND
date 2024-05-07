package com.erichgamma.api.team.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.erichgamma.api.team.model.TeamDto;
    
public interface TeamDAO{

    TeamDto findDSL(Long id);
    void insertDSL(TeamDto team);
    void updateDSL(TeamDto team);

    List<TeamDto> getAllTeamsDSL(Pageable pageable);
    List<Map<String, String>> getEmptyPositionDSL();
}
