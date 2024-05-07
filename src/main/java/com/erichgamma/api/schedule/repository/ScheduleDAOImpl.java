package com.erichgamma.api.schedule.repository;

import com.erichgamma.api.schedule.model.QSchedule;
import com.erichgamma.api.schedule.model.QScheduleDto;
import com.erichgamma.api.schedule.model.ScheduleDto;

import com.erichgamma.api.stadium.model.QStadium;
import com.erichgamma.api.team.model.QTeam;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


import java.util.List;

@RequiredArgsConstructor
public class ScheduleDAOImpl implements ScheduleDAO {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSchedule schedule = QSchedule.schedule;
    private final QStadium stadium = QStadium.stadium;
    private final QTeam team = QTeam.team;

    @Override
    public List<ScheduleDto> getAllSchedule() {

        return jpaQueryFactory.select(
                        new QScheduleDto(
                                schedule.id,
                                schedule.scheDate,
                                schedule.gubun,
                                schedule.hometeamId,
                                schedule.awayteamId,
                                schedule.homeScore,
                                schedule.awayScore,
                                schedule.stadiumId.stadiumId))

                .from(schedule)
                .fetch();

    }

    @Override
    public List<?> getScheduleByDateDsl() {
        return jpaQueryFactory.select(stadium.stadiumName)
                .distinct()
                .from(schedule)
                .join(stadium).on(schedule.stadiumId.stadiumId.eq(stadium.stadiumId))
                .where(schedule.scheDate.between("20120501", "20120531"))
                .fetch();
    }

    @Override
    public List<?> getScoregepByScheDateDsl() {
        return jpaQueryFactory
                .select(stadium.stadiumName, schedule.scheDate, team.eTeamName, team.teamName)
                .from(schedule)
                .join(stadium).on(schedule.stadiumId.stadiumId.eq(stadium.stadiumId))
                .join(team).on(schedule.hometeamId.eq(team.teamId))
                .join(team).on(schedule.awayteamId.eq(team.teamId))
                .where(schedule.homeScore.subtract(schedule.awayScore).goe(3))
                .fetch();
    }

}
