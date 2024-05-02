package com.erichgamma.api.team.repository;

import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import com.erichgamma.api.team.model.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@PersistenceContext
@RequiredArgsConstructor
public class TeamDAOImpl implements TeamDAO{
    private final EntityManager teamEntityManager;

    @Override
    public Team find(Long id) {
        return teamEntityManager.find(Team.class, id);
    }

    @Override
    public void insert(Team team) {
        teamEntityManager.persist(team);
    }

    @Override
    public void update(Team team) {
        teamEntityManager.persist(team);
    }

    
}
