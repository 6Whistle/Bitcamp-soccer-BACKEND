package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Map;


public interface ScheduleJpqlRepository extends JpaRepository<Schedule,Long> {



    @Query("SELECT distinct new map ( s.stadiumId.stadiumName )FROM schedule s WHERE s.scheDate BETWEEN :startDate AND :endDate")
    Map<String,List<String>> getScheduleByDate( );


}
