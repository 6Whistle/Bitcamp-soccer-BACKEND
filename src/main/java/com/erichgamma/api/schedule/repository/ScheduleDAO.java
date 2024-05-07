package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.Schedule;
import com.erichgamma.api.schedule.model.ScheduleDto;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ScheduleDAO {


    List<ScheduleDto> getAllSchedule();
    List<?> getScheduleByDateDsl();

    List<?> getScoregepByScheDateDsl();
   }


