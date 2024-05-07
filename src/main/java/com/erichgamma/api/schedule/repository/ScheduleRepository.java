package com.erichgamma.api.schedule.repository;



import com.erichgamma.api.schedule.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScheduleRepository extends ScheduleJpqlRepository,ScheduleDAO {




}

