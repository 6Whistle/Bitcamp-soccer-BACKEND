package com.erichgamma.api.team.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.erichgamma.api.player.model.PlayerDto;
import com.erichgamma.api.player.model.QPlayer;
import com.erichgamma.api.player.model.QPlayerDto;
import com.erichgamma.api.team.model.QTeam;
import com.erichgamma.api.team.model.QTeamDto;
import com.erichgamma.api.team.model.TeamDto;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamDAOImpl implements TeamDAO{

    private final JPAQueryFactory queryFactory;
    private final QTeam team = QTeam.team;
    private final QPlayer player = QPlayer.player;

    @Override
    public TeamDto findDSL(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public void insertDSL(TeamDto team) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void updateDSL(TeamDto team) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<TeamDto> getAllTeamsDSL(Pageable pageable) {
        return queryFactory.select(new QTeamDto(team.id, team.teamId, team.regionName, team.teamName, team.eTeamName, 
        team.origYyyy, team.zipCode1, team.zipCode2, team.address, team.ddd, 
        team.tel, team.fax, team.homepage, team.owner, team.stadiumId.stadiumId))
        .from(team)
        .orderBy(
            pageable.getSort().stream()
            .map(i -> new OrderSpecifier(i.isAscending() ? Order.ASC : Order.DESC, new PathBuilder(team.getType(), team.getMetadata()).get(i.getProperty())))
            .toArray(OrderSpecifier[]::new)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
    }

    @Override
    public List<Map<String, String>> getEmptyPositionDSL() {
        return queryFactory.select(player.playerName, team.teamName)
                            .from(team)
                            .join(player)
                            .on(team.teamId.eq(player.teamId.teamId))
                            .fetchJoin()
                            .where(player.position.eq(""))
                            .fetch().stream()
                            .map(i -> Map.of("teamName", i.get(team.teamName), "playerName", i.get(player.playerName)))
                            .toList();
    }

    @Override
    public Long getCountDSL() {
        return queryFactory.select(team.count()).from(team).fetchOne();
    }

    @Override
    public List<PlayerDto> getPlayersHavingHeightDSL() {
        return queryFactory.select(new QPlayerDto(player.id, player.position, player.ePlayerName, player.backNo, player.playerId, 
                                                player.birthDate, player.height, player.joinYyyy, player.name, player.nation, 
                                                player.solar, player.weight, player.nickname, player.playerName, player.teamId.teamId))
                .from(team)
                .join(player)
                .on(team.teamId.eq(player.teamId.teamId))
                .fetchJoin()
                .where(player.height.ne(""))
                .fetch();
    }

    @Override
    public List<Map<String, Object>> getHeightAvgByTeamDSL() {
        return queryFactory.select(team.teamId.as("팀ID"), team.teamName.as("팀명"), player.height.castToNum(Long.class).avg().multiply(100).round().divide(100).as("평균"))
        .from(team)
        .join(player)
        .on(team.teamId.eq(player.teamId.teamId))
        .fetchJoin()
        .groupBy(team.teamId, team.teamName)
        .fetch()
        .stream()
        .map(i -> Map.of("팀ID", i.get(0, Object.class), "팀명", i.get(1, Object.class), "평균", i.get(2, Object.class)))
        .toList();
    }

}
