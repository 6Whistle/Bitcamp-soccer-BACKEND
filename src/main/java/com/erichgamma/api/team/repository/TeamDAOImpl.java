package com.erichgamma.api.team.repository;

import java.util.List;

import com.erichgamma.api.team.model.QTeam;
import com.erichgamma.api.team.model.TeamDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamDAOImpl implements TeamDAO{

    private final JPAQueryFactory queryFactory;

    @Override
    public TeamDto find(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return queryFactory.selectFrom(QTeam.team)
            .fetch()
            .stream()
            .map(i -> TeamDto.builder()
            .id(i.getId())
            .teamId(i.getTeamId())
            .teamName(i.getTeamName())
            .eTeamName(i.getETeamName())
            .origYyyy(i.getOrigYyyy())
            .zipCode1(i.getZipCode1())
            .zipCode2(i.getZipCode2())
            .address(i.getAddress())
            .ddd(i.getDdd())
            .tel(i.getTel())
            .fax(i.getFax())
            .homepage(i.getHomepage())
            .owner(i.getOwner())
            .stadiumId(i.getStadiumId().getStadiumId())
            .build())
        .toList();
    }

    @Override
    public void insert(TeamDto team) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(TeamDto team) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
