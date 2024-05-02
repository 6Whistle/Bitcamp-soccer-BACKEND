package com.erichgamma.api.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erichgamma.api.team.model.Team;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Repository
public interface TeamRepository extends TeamJpqlRepository, JpaRepository<Team, Long>, TeamDAO{
    // public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("team");
}
