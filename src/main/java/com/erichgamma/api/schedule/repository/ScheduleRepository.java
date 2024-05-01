package com.erichgamma.api.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erichgamma.api.schedule.model.Schedule;

import java.util.List;
import java.util.Map;

@Repository
public interface ScheduleRepository extends ScheduleJpqlRepository {




}

