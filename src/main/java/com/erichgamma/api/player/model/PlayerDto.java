package com.erichgamma.api.player.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@Builder
@NoArgsConstructor
public class PlayerDto {
    private Long id;
    private String position;
    private String ePlayerName;
    private String backNo;
    private String playerId;
    private String birthDate;
    private String height;
    private String joinYyyy;
    private String name;
    private String nation;
    private String solar;
    private String weight;
    private String nickname;
    private String playerName;
    private String teamId;

    @QueryProjection
    public PlayerDto(Long id, String position, String ePlayerName, String backNo, String playerId, String birthDate, String height, String joinYyyy, String name, String nation, String solar, String weight, String nickname, String playerName, String teamId) {
        this.id = id;
        this.position = position;
        this.ePlayerName = ePlayerName;
        this.backNo = backNo;
        this.playerId = playerId;
        this.birthDate = birthDate;
        this.height = height;
        this.joinYyyy = joinYyyy;
        this.name = name;
        this.nation = nation;
        this.solar = solar;
        this.weight = weight;
        this.nickname = nickname;
        this.playerName = playerName;
        this.teamId = teamId;
    }
    @QueryProjection
    public PlayerDto(String position) {
        this.position = position;
    }

    @QueryProjection
    public PlayerDto(String height, String name, String weight) {
        this.height = height;
        this.name = name;
        this.weight = weight;
    }
}
