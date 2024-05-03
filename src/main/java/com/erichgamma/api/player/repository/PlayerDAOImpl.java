package com.erichgamma.api.player.repository;


import com.erichgamma.api.player.model.PlayerDto;
import com.erichgamma.api.player.model.QPlayer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


import java.util.List;
@RequiredArgsConstructor
public class PlayerDAOImpl implements PlayerDAO {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<PlayerDto> getAllPlayer() {
        return jpaQueryFactory.select(
                QPlayer.player.id
                        ,QPlayer.player.position
                ,QPlayer.player.ePlayerName
                ,QPlayer.player.backNo
                ,QPlayer.player.playerId
                ,QPlayer.player.birthDate
                ,QPlayer.player.height
                ,QPlayer.player.joinYyyy
                ,QPlayer.player.name
                ,QPlayer.player.nation
                ,QPlayer.player.solar
                ,QPlayer.player.weight
                ,QPlayer.player.nickname
                ,QPlayer.player.teamId.teamId).from(QPlayer.player).fetch()
                .stream().map(tuple -> PlayerDto.builder()
                        .playerName(tuple.get(QPlayer.player.playerName))
                        .playerId(tuple.get(QPlayer.player.playerId))
                        .birthDate(tuple.get(QPlayer.player.birthDate))
                        .ePlayerName(tuple.get(QPlayer.player.ePlayerName))
                        .id(tuple.get(QPlayer.player.id))
                        .height(tuple.get(QPlayer.player.height))
                        .weight(tuple.get(QPlayer.player.weight))
                        .name(tuple.get(QPlayer.player.name))
                        .nickname(tuple.get(QPlayer.player.nickname))
                        .solar(tuple.get(QPlayer.player.solar))
                        .nation(tuple.get(QPlayer.player.nation))
                        .backNo(tuple.get(QPlayer.player.backNo))
                        .teamId(tuple.get(QPlayer.player.teamId.teamId))
                        .build())
        .toList();
    }
}
