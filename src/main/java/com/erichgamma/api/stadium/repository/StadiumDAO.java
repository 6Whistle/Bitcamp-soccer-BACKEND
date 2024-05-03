package com.erichgamma.api.stadium.repository;

import com.erichgamma.api.stadium.model.Stadium;
import jakarta.persistence.EntityManager;

public interface StadiumDAO extends EntityManager {

    void insert(Stadium stadium);
    void update(Stadium stadium);
}