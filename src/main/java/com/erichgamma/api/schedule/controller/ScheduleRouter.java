package com.erichgamma.api.schedule.controller;


import com.erichgamma.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ScheduleRouter {

    private final ScheduleRepository scheduleRepository;

    public List<Map<String, Object>> execute(String q) {

        return switch (q) {
            case "16" -> scheduleRepository.getScoregepByScheDate();
            case "23" -> scheduleRepository.getScheduleByDate();
            default -> List.of();

        };

    }
}
