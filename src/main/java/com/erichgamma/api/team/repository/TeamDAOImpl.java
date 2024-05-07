package com.erichgamma.api.team.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.erichgamma.api.player.model.QPlayer;
import com.erichgamma.api.team.model.QTeam;
import com.erichgamma.api.team.model.QTeamDto;
import com.erichgamma.api.team.model.TeamDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
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

    @Override
    public List<TeamDto> getAllTeamsDSL(Pageable pageable) {
        JPAQuery<TeamDto> query = queryFactory.select(new QTeamDto(team.id, team.teamId, team.regionName, team.teamName, team.eTeamName, 
                                                team.origYyyy, team.zipCode1, team.zipCode2, team.address, team.ddd, 
                                                team.tel, team.fax, team.homepage, team.owner, team.stadiumId.stadiumId))
                                                .from(team);
        
        for(Sort.Order o : pageable.getSort())
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, new PathBuilder(team.getType(), team.getMetadata()).get(o.getProperty())));
                
        return query.offset(pageable.getOffset())
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
}
