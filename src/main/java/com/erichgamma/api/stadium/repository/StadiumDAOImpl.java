package com.erichgamma.api.stadium.repository;

import com.erichgamma.api.stadium.model.QStadium;
import com.erichgamma.api.stadium.model.QStadiumDto;
import com.erichgamma.api.stadium.model.StadiumDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static com.erichgamma.api.player.model.QPlayer.player;
import static com.erichgamma.api.schedule.model.QSchedule.schedule;
import static com.erichgamma.api.team.model.QTeam.team;

@RequiredArgsConstructor
public class StadiumDAOImpl implements StadiumDAO {

    private final JPAQueryFactory queryFactory;

    private final QStadium stadium = QStadium.stadium;

    @Override
    public List<StadiumDto> getAllStadiumsDSL() {
        return queryFactory.select(new QStadiumDto(
                        stadium.id,
                        stadium.stadiumId,
                        stadium.stadiumName,
                        stadium.hometeamId,
                        stadium.seatCount,
                        stadium.address,
                        stadium.ddd,
                        stadium.tel)
                )
                .from(stadium)
                .fetch();
    }

    @Override
    public List<Map<String, String>> stadiumNameWithTeamDSL() {
        return queryFactory.select(stadium.stadiumName, team.regionName.concat("[ ]").concat(team.teamName))
                .from(stadium)
                .join(team)
                .on(stadium.stadiumId.eq(team.stadiumId.stadiumId))
                .where(team.regionName.eq("수원").and(stadium.stadiumName.like("수원%")))
                .distinct()
                .fetch().stream().map(i -> Map.of("홈구장", i.get(stadium.stadiumName),
                        "팀명", i.get(team.regionName.concat("[ ]").concat(team.teamName))))
                .toList()
                ;
    }

    @Override
    public List<Map<String, String>> stadiumAndTeamAndScheduleDSL() {
        return queryFactory
                .select(stadium.stadiumName,
                        team.teamName,
                        JPAExpressions.select(team.teamName)
                                .from(team).where(schedule.awayteamId.eq(team.teamId))
                )
                .from(stadium)
                .join(team).on(stadium.stadiumId.eq(team.stadiumId.stadiumId))
                .join(schedule).on(stadium.stadiumId.eq(schedule.stadiumId.stadiumId))
                .where(schedule.scheDate.eq("20120317")).distinct()
                .fetch()
                .stream().map(i -> Map.of("스타디움", i.get(stadium.stadiumName),
                        "팀이름", i.get(team.teamName),
                        "어웨이팀", i.get(JPAExpressions.select(team.teamName)
                                .from(team).where(schedule.awayteamId.eq(team.teamId)))
                )).toList()
                ;
    }

    @Override
    public List<Map<String, String>> pohangSteelersGkDSL() {
        return queryFactory.select(player.playerName, player.position,
                        team.teamName.concat(" ").concat(team.regionName),
                        stadium.stadiumName, schedule.scheDate
                )
                .from(stadium)
                .join(schedule).on(stadium.stadiumId.eq(schedule.stadiumId.stadiumId))
                .join(team).on(stadium.stadiumId.eq(team.stadiumId.stadiumId))
                .join(player).on(team.teamId.eq(player.teamId.teamId))
                .where(player.position.eq("GK").and(team.teamName.eq("스틸러스")).and(schedule.scheDate.eq("20120317")))
                .fetch().stream().map(i -> Map.of("선수명", i.get(player.playerName),
                        "포지션", i.get(player.position), "팀명", i.get(team.teamName.concat(" ").concat(team.regionName)),
                        "스타디움", i.get(stadium.stadiumName), "경기날짜", i.get(schedule.scheDate)
                )).toList();
    }

    @Override
    public List<Map<String, String>> homeTeamWinDSL() {
        return queryFactory.select(stadium.stadiumName,
                        schedule.scheDate, team.teamName)
                .from(stadium)
                .join(schedule).on(stadium.stadiumId.eq(schedule.stadiumId.stadiumId))
                .join(team).on(team.teamId.eq(schedule.hometeamId))
                .join(team).on(team.teamId.eq(schedule.awayteamId))
                .where(schedule.homeScore.subtract(schedule.awayScore).goe(3))
                .fetch()
                .stream().map(i-> Map.of("스타디움", i.get(stadium.stadiumName),
                        "경기일정", i.get(schedule.scheDate), "팀명", i.get(team.teamName)
                        ))
                .toList();
    }

    @Override
    public List<Map<String, String>> noHomeTeamDSL() {
        return queryFactory.select(stadium.stadiumName,
                JPAExpressions.select(team.teamName)
                        .from(team)
                        .where(team.stadiumId.stadiumId.eq(stadium.stadiumId)))
                .from(stadium)
                .leftJoin(team).on(stadium.stadiumId.eq(team.stadiumId.stadiumId))
                .fetch().stream().map(i-> Map.of("스타디움", i.get(stadium.stadiumName),
                        "팀명", i.get(JPAExpressions.select(team.teamName).from(team)
                                .where(team.stadiumId.stadiumId.eq(stadium.stadiumId))))).toList();
    }

    @Override
    public Long countAllStadiums() {
        return queryFactory.select(stadium.count()).from(stadium).fetchFirst();
    }
}
