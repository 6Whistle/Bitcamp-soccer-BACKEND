package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ScheduleJpqlRepository extends JpaRepository<Schedule, Long> {


//    @Query("SELECT distinct new map(s.stadiumName AS 경기장)" +
//            "FROM schedule sch\n" +
//            "JOIN stadium s ON sch.stadiumId.stadiumId = s.stadiumId\n" +
//            "WHERE sch.scheDate BETWEEN '20120501' AND '20120531' ")
//    List<Map<String, Object>> getScheduleByDate();
//
//    @Query("SELECT DISTINCT new map(s.stadiumName AS 경기장, sch.scheDate as 경기날짜, thome.teamName AS 홈팀,\n" +
//            "taway.teamName AS 어웨이팀)" +
//            "FROM schedule sch\n" +
//            "JOIN stadium s ON sch.stadiumId.stadiumId = s.stadiumId\n" +
//            "JOIN team thome ON sch.hometeamId = thome.teamId\n" +
//            "JOIN team taway ON scdh.awayteamId = taway.teamId\n" +
//            "WHERE (sch.homeScore - sch.awayScore) >= 3")
//    List<Map<String, Object>> getScoregepByScheDate();
//




}


//-- 문제 16
//        -- 홈팀이 3점이상 차이로 승리한 경기의
//-- 경기장 이름, 경기 일정
//-- 홈팀 이름과 원정팀 이름을
////-- 구하시오

//20120727
////-- 문제 23
////-- 2012년 5월 한달간 경기가 있는 경기장  조회
//SELECT DISTINCT (SELECT s.stadium_name
//        FROM stadium s
////        WHERE s.stadium_id = sch.stadium_id) '경기장'
//FROM schedule sch
//WHERE YEAR(sch.sche_date) = 2012
//    AND MONTH(sch.sche_date) = 5

