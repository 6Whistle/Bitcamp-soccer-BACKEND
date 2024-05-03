package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.QSchedule;
import com.erichgamma.api.schedule.model.ScheduleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScheduleDAOImpl implements ScheduleDAO {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ScheduleDto> getAllSchedule() {

        return jpaQueryFactory.select(
                        QSchedule.schedule.id,
                        QSchedule.schedule.scheDate,
                        QSchedule.schedule.gubun,
                        QSchedule.schedule.homeScore,
                        QSchedule.schedule.awayScore,
                        QSchedule.schedule.hometeamId,
                        QSchedule.schedule.awayteamId,
                        QSchedule.schedule.stadiumId)
                .from(QSchedule.schedule)
                .fetch()
                .stream()
                .map(tuple -> ScheduleDto.builder()
                        .id(tuple.get(QSchedule.schedule.id))
                        .scheDate(tuple.get(QSchedule.schedule.scheDate))
                        .gubun(tuple.get(QSchedule.schedule.gubun))
                        .homeScore(tuple.get(QSchedule.schedule.homeScore))
                        .awayScore(tuple.get(QSchedule.schedule.awayScore))
                        .hometeamId(tuple.get(QSchedule.schedule.hometeamId))
                        .awayteamId(tuple.get(QSchedule.schedule.awayteamId))
                        .stadiumId(tuple.get(QSchedule.schedule.stadiumId.stadiumId))
                        .build())
                .toList();
    }
}
