package com.erichgamma.api.schedule.controller;


import com.erichgamma.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ScheduleRouter {

    private final ScheduleRepository scheduleRepository;

    public List<?> execute(String q) {

        return switch (q) {
//            case "16" -> scheduleRepository.getScoregepByScheDate();
//
            case "findAll" -> scheduleRepository.getAllSchedule();
            case "16" -> scheduleRepository.getScheduleByDateDsl();
            case "22" -> scheduleRepository.getScoregepByScheDateDsl();
            default -> List.of();

        };

    }
}
