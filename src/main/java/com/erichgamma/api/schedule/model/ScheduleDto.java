package com.erichgamma.api.schedule.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Log4j2
@Builder
public class ScheduleDto {
    private Long id;
    private String scheDate;
    private String gubun;
    private String hometeamId;
    private String awayteamId;
    private Integer homeScore;
    private Integer awayScore;
}
