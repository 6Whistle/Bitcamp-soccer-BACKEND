package com.erichgamma.api.player.repository;


import com.erichgamma.api.player.model.PlayerDto;
import com.erichgamma.api.player.model.QPlayer;
import com.erichgamma.api.player.model.QPlayerDto;

import com.erichgamma.api.team.model.QTeam;
import com.querydsl.core.types.ExpressionUtils;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Map;

import static com.erichgamma.api.team.model.QTeam.team;

@RequiredArgsConstructor
public class PlayerDAOImpl implements PlayerDAO {

    private final JPAQueryFactory jpaQueryFactory;
    private  final  QPlayer player = QPlayer.player;
    private  final QTeam team = QTeam.team;
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

//        return jpaQueryFactory
//                .select(
//                        Expressions.asMap(
//                                player.playerName.as("playerName"),
//                                Expressions.stringTemplate("IFNULL(NULLIF({0}, ''), '0')", player.height).as("키"),
//                                Expressions.stringTemplate("IFNULL(NULLIF({0}, ''), '0')", player.weight).as("몸무게"),
//                                Expressions.stringTemplate(
//                                        "CASE WHEN ({0} = '') OR ({1} = '') THEN 'NONE' ELSE CAST(round(CAST({1} AS DOUBLE) / CAST(POW(CAST({0} AS DOUBLE) / 100.0, 2) AS DOUBLE), 2) AS string) END",
//                                        player.height, player.weight
//                                ).as("BMI")
//                        )
//                )
//                .from(player)
//                .join(player.teamId, team)
//                .where(team.regionName.eq("서울"))
//                .fetch();
        return null;
    }



    @Override
    public List<Map<String, String>> getOnPositionAndTeamId10DSL() {




        return jpaQueryFactory
                .select(player.position, player.teamId.teamId, player.playerName)
                .from(player)
                .where(player.position.eq("GK")
                        .and(player.teamId.teamId.in("K02", "K10")))
                .orderBy(
//                        jpaQueryFactory.select(team.teamName)
//                                .from(team)
//                                .where(team.teamId.eq(player.teamId.teamId)).fetch(),
                        player.playerName.asc()
                )
                .fetch().stream().map(i->Map.of("playerName",i.get(player.playerName),"teamId",i.get(player.teamId.teamId),"position",i.get(player.position))).toList();
    }

    @Override
    public List<Map<String , String>> getOnCountAll(Pageable pageable) {

        return jpaQueryFactory
                .select(
                player.teamId.teamId,player.playerName
                ,player.position,player.name,player.backNo
                ,player.nickname,player.solar,player.ePlayerName
        ).from(player).offset(0).limit(3).fetch().stream().map(i->Map.of("teamId",i.get(player.teamId.teamId),"playerName",i.get(player.playerName)
        ,"position",i.get(player.position),"name",i.get(player.name),"backNo",i.get(player.backNo),"nickname",i.get(player.nickname)
        ,"solar",i.get(player.solar),"ePlayerName",i.get(player.ePlayerName)
        )).toList();
    }

    @Override
    public List<Map<String , String>> getOnPositionAndTeamId20DSL() {
        return jpaQueryFactory.select(team.teamName, player.playerName, player.backNo)
                .from(player)
                .leftJoin(player.teamId, team)
                .where(player.position.eq("MF"))
                .fetch().stream().map(i->Map.of("teamName",i.get(team.teamName),"playerName",i.get(player.playerName),"backNo",i.get(player.backNo))).toList();
    }

    @Override
    public List<Map<String , String>> getOnPositionAndTeamId21DSL() {
        return jpaQueryFactory.select(team.teamName, player.playerName, player.backNo)
                .from(player)
                .leftJoin(player.teamId, team)
                .where(player.position.eq("MF"))
                .offset(0)
                .limit(3)
                .fetch().stream().map(i->Map.of("teamName",i.get(team.teamName),"playerName",i.get(player.playerName),"backNo",i.get(player.backNo))).toList();
    }

    @Override
    public List<Map<String , String>> getOnPositionAndTeamId2DSL() {
        return jpaQueryFactory.select(player.position,player.teamId.teamId).from(player)
                .where(player.position.eq("GK").and(player.teamId.teamId.eq(
                        jpaQueryFactory.select(team.teamId).from(team).where(team.regionName.eq("수원")))))
                .fetch().stream().map(i->Map.of("position",i.get(player.position),"teamId",i.get(player.teamId.teamId)
                )).toList();
    }

    @Override
    public List<Map<String , String>> getOnPositionAndTeamIdAndHeightDSL() {
        return jpaQueryFactory.select(player.teamId.teamId,player.height,player.playerName)
                .from(player)
                .where(player.height.isNotEmpty()
                        .and(player.playerName.like("고%"))
                        .and(player.teamId.teamId
                                .eq(jpaQueryFactory.select(team.teamId)
                                        .from(team)
                                        .where(team.regionName
                                                .eq("수원"))
                                ))).fetch()
                .stream().map(i->Map.of(
                        "teamId",i.get(player.teamId.teamId),"height",i.get(player.height)
                        ,"playerName",i.get(player.playerName)
                )).toList();
    }

    @Override
    public List<Map<String , String>> getOnPositionAndHeightAndTeamIdDSL() {
        return jpaQueryFactory.select(
                player.playerName,player.position,player.teamId.teamId
                ,player.height
        ).from(player)
                .where(player.position.eq("MF")
                        .and(player.height.isNotEmpty())
                        .and(player.teamId.teamId
                                .in(jpaQueryFactory.select(
                                        team.teamId
                                        )
                                        .from(team)
                                        .where(team.teamId
                                                .in("삼성블루윙즈","드래곤즈"))))).fetch()
                .stream()
                .map(i->Map.of("playerName",i.get(player.playerName),"position",i.get(player.position)
                        ,"teamId",i.get(player.teamId.teamId),"height",i.get(player.height)))
                .toList();
    }

    @Override
    public List<Map<String , String>> getPositionAndeRegionDSL() {
        return jpaQueryFactory.select(
                player.position,player.teamId.teamId
        ).from(player)
                .where(player.position.eq("GK")
                        .and(player.teamId.teamId
                                .eq(jpaQueryFactory.select(team.teamId)
                                        .from(team)
                                        .where(team.regionName
                                                .eq("수원")))))
                .fetch().stream()
                .map(i->Map.of("position",i.get(player.position)
                ,"teamId",i.get(player.teamId.teamId))).toList();
    }

    @Override
    public List<Map<String , String>> getHeightAndTeamIdDSL() {
        QPlayer Player = new QPlayer("subPlayer");
        return jpaQueryFactory.select(
                player.height,player.teamId.teamId
                ,player.ePlayerName,player.playerId
        ).from(player)
                .where(player.height.castToNum(double.class).lt(
                                jpaQueryFactory
                                        .select(Player.height.castToNum(double.class).avg())
                                        .from(Player)
                                        .where(Player.teamId.teamId.eq(player.teamId.teamId))
                        )
                )
                .fetch().stream().map(i->Map.of(
                        "height",i.get(player.height),"teamId",i.get(player.teamId.teamId)
                        ,"ePlayerName",i.get(player.ePlayerName),"playerId",i.get(player.playerId)
                )).toList();
    }


    @Override
    public Long countAllPlayers() {
       Long count = jpaQueryFactory.select( player.count()).from(player).fetchFirst();
        return count;
    }



}
