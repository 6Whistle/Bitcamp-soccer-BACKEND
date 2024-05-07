package com.erichgamma.api.schedule.model;


import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
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
    private String stadiumId;

    @QueryProjection
    public ScheduleDto(Long id, String scheDate, String gubun,
                       String hometeamId, String awayteamId, Integer homeScore, Integer awayScore, String stadiumId) {
        this.id = id;
        this.scheDate = scheDate;
        this.gubun = gubun;
        this.hometeamId = hometeamId;
        this.awayteamId = awayteamId;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.stadiumId = stadiumId;
    }




}
