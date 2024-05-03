package com.erichgamma.api.player.model;

import com.erichgamma.api.team.model.Team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "player")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "id")
public class Player {
    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true, referencedColumnName = "teamId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Team teamId;
}
