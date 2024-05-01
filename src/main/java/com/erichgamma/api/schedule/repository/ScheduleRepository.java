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

////-- 문제 23
////-- 2012년 5월 한달간 경기가 있는 경기장  조회
//SELECT DISTINCT (SELECT s.stadium_name
//        FROM stadium s
//        WHERE s.stadium_id = sch.stadium_id) '경기장'
//FROM schedule sch
//WHERE YEAR(sch.sche_date) = 2012
//    AND MONTH(sch.sche_date) = 5