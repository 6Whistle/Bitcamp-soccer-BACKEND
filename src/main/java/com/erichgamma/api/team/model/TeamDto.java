package com.erichgamma.api.team.model;

import org.springframework.stereotype.Component;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Component
@ToString
@Builder
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String teamId;
    private String regionName;
    private String teamName;
    private String eTeamName;
    private String origYyyy;
    private String zipCode1;
    private String zipCode2;
    private String address;
    private String ddd;
    private String tel;
    private String fax;
    private String homepage;
    private String owner;
    private String stadiumId;

    @QueryProjection
    public TeamDto(Long id, String teamId, String regionName, String teamName, String eTeamName, String orgYyyy, String zipCode1, 
        String zipCode2, String address, String ddd, String tel, String fax, String homepage, String owner, String stadiumId){
            this.id = id;
            this.teamId = teamId;
            this.regionName = regionName;
            this.teamName = teamName;
            this.eTeamName = eTeamName;
            this.origYyyy = orgYyyy;
            this.zipCode1 = zipCode1;
            this.zipCode2 = zipCode2;
            this.address = address;
            this.ddd = ddd;
            this.tel = tel;
            this.fax = fax;
            this.homepage = homepage;
            this.owner = owner;
            this.stadiumId = stadiumId;
    }
}
