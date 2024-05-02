package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.Schedule;

public interface ScheduleDAO {


    Schedule sch(Schedule schedule, Long id);

    void insert(Schedule schedule);
    void update(Schedule schedule);
}
