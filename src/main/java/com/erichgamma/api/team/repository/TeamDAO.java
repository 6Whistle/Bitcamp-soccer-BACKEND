package com.erichgamma.api.team.repository;

import com.erichgamma.api.team.model.Team;

public interface TeamDAO{
    Team find(Long id);

    void insert(Team team);
    void update(Team team);
}
