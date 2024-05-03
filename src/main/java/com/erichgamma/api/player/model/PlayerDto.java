package com.erichgamma.api.player.model;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
