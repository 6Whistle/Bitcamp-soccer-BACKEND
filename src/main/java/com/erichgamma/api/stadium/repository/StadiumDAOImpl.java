package com.erichgamma.api.stadium.repository;

import com.erichgamma.api.stadium.model.QStadium;
import com.erichgamma.api.stadium.model.QStadiumDto;
import com.erichgamma.api.stadium.model.StadiumDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.*;

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
    public List<?> stadiumAndTeamAndScheduleDSL() {
        return List.of();
    }

    @Override
    public List<?> pohangSteelersGkDSL() {
        return List.of();
    }

    @Override
    public List<?> homeTeamWinDSL() {
        return List.of();
    }

    @Override
    public List<?> noHomeTeamDSL() {
        return List.of();
    }
}
