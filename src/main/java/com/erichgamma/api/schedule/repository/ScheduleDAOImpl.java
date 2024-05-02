package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@PersistenceContext
public class ScheduleDAOImpl implements ScheduleDAO  {


    private final EntityManager entityManager;

    @Override
    public Schedule sch(Schedule schedule, Long id) {
        return entityManager.find(Schedule.class, 1L);
    }

    @Override
    public void insert(Schedule schedule) {
        entityManager.persist(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        entityManager.merge(schedule);
    }
}
