package com.erichgamma.api.player.repository;


import com.erichgamma.api.player.model.PlayerDto;
import com.erichgamma.api.player.model.QPlayer;
import com.erichgamma.api.player.model.QPlayerDto;

import com.querydsl.core.types.ExpressionUtils;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;



import java.util.List;
import java.util.Map;

import static com.erichgamma.api.team.model.QTeam.team;

@RequiredArgsConstructor
public class PlayerDAOImpl implements PlayerDAO {

    private final JPAQueryFactory jpaQueryFactory;
    private  final  QPlayer player = QPlayer.player;
    @Override
    public List<PlayerDto> getAllPlayer() {
        return jpaQueryFactory.select(
                new QPlayerDto(
                        player.id
                        ,player.position
                        ,player.ePlayerName
                        ,player.backNo
                        ,player.playerId
                        ,player.birthDate
                        ,player.height
                        ,player.joinYyyy
                        ,player.name
                        ,player.nation
                        ,player.solar
                        ,player.weight
                        ,player.nickname
                        ,player.playerName
                        ,player.teamId.teamId)).
                from(player).fetch();

    }

    @Override
    public List<?> getOnPositionDSL() {
        return jpaQueryFactory.select(player.position)
                .from(player).distinct().orderBy().offset(0).limit(5)
                .fetch();
    } //dto를 constructor 생성하는 방법1.

//    @Override
//    public List<?> getOnPositionDSL() {
//        return jpaQueryFactory.select(
//                player.position).
//                from(player).distinct().offset(0).limit(5).fetch();
//    } // new query없이  단일 개체로 처리 하는 방법2. (대신 이것은 인자가 하나일때 사용 2개이상이면 tuple)

    @Override
    public List<?> getOnPositionNotNullDSL() {

        return jpaQueryFactory.select(
                player.position.coalesce("신입").as("포지션")
        ).from(player).distinct().fetch();
    }

    @Override
    public List<String> getOnPositionAndTeamIdDSL() {
        return jpaQueryFactory
                .select(
                        player.teamId.teamId.stringValue().concat(", ").concat(player.position)
                )
                .from(player)
                .where(player.position.eq("GK").and(player.teamId.teamId.eq("k02")))
                .fetch();
    }// 방법 인자가 2개 이상일때 .concat으로 묶어서 하나의 string으로 처리하느 방법3.

    @Override
    public List<Map<String, String>> getOnPositionAndTeamId7DSL() {
        String seoulTeamId = jpaQueryFactory.select(team.teamId)
                .from(team)
                .where(team.regionName.eq("서울"))
                .fetchFirst();

        return jpaQueryFactory.select(
                player.height.coalesce("0").as("키"),
                player.weight.coalesce("0").as("몸무게"),
                player.playerName
                ).from(player)
                .where(player.teamId.teamId.eq(seoulTeamId))
                .fetch().stream().map(i -> Map.of("playerName",i.get(player.playerName),
                        "키", i.get(ExpressionUtils.as(player.height.coalesce("0"), "키")),
                        "몸무게", i.get(ExpressionUtils.as(player.weight.coalesce("0"), "몸무게"))
                )).toList();
    } //방법4.map으로 묶어서 다음과 같이 출력하는 방법


    @Override
    public List<?> getOnHeightAndWeightDSL() {

        return jpaQueryFactory.select(
                player.playerName,
                player.weight.coalesce("0").as("키"),
                player.height.coalesce("0").as("몸무게")

        ).from().fetch();

    }

    @Override
    public List<Map<String, String>> getOnPositionAndTeamId10DSL() {



        return null;

//        return jpaQueryFactory.select(
//                player.position,
//                player.teamId.teamId,
//                player.playerName
//        ).from(player).where(player.position.eq("GK").and(player.teamId.teamId.in("K02","K10")))
//                .orderBy((OrderSpecifier<?>) jpaQueryFactory.select(team.teamName).from(team).where(team.teamId.eq(player.teamId.teamId)).fetch(),player.playerName.asc())
//                .fetch().stream().map(i->Map.of("playerName",i.get(player.playerName),"teamId",i.get(player.teamId.teamId),"position",i.get(player.position))).toList();
    }


}
