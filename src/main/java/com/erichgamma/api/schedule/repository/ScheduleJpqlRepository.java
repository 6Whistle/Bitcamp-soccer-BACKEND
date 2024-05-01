package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Map;


public interface ScheduleJpqlRepository extends JpaRepository<Schedule, Long> {


    @Query("SELECT distinct new map((SELECT DISTINCT s.stadiumName FROM stadium s WHERE s.stadiumId = sch.stadiumId)" +
            "as stadiumName) FROM schedule sch WHERE YEAR(sch.scheDate) = 2012 AND MONTH(sch.scheDate) = 5")
    List<Map<String, Object>> getScheduleByDate();

    @Query("select new map(s.stadiumName AS stadium,\n" +
            "sch.scheDate,\n" +
            "thome.teamName AS home,\n" +
            "taway.teamName AS away)" +
            "FROM schedule sch\n" +
            "JOIN stadium s ON sch.stadiumId = s.stadiumId\n" +
            "JOIN team thome ON sch.hometeamId = thome.teamId\n" +
            "JOIN team taway ON sch.awayteamId = taway.teamId\n" +
            "WHERE (sch.homeScore - sch.awayScore) >= 3")
    List<Map<String, Object>> getScoregepByScheDate();


}


//-- 문제 16
//        -- 홈팀이 3점이상 차이로 승리한 경기의
//-- 경기장 이름, 경기 일정
//-- 홈팀 이름과 원정팀 이름을
////-- 구하시오


////-- 문제 23
////-- 2012년 5월 한달간 경기가 있는 경기장  조회
//SELECT DISTINCT (SELECT s.stadium_name
//        FROM stadium s
////        WHERE s.stadium_id = sch.stadium_id) '경기장'
//FROM schedule sch
//WHERE YEAR(sch.sche_date) = 2012
//    AND MONTH(sch.sche_date) = 5

