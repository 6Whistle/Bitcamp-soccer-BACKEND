package com.erichgamma.api.team.repository;

import java.util.List;

import com.erichgamma.api.team.model.TeamDto;

public interface TeamDAO{
    TeamDto find(Long id);

    List<TeamDto> getAllTeams();

    void insert(TeamDto team);
    void update(TeamDto team);
}
