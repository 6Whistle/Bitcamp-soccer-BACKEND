package com.erichgamma.api.player.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PlayerDAO {

    List<?> getAllPlayer();

    List<?> getOnPositionDSL();

    List<?> getOnPositionNotNullDSL();

    List<?> getOnPositionAndTeamIdDSL();

    List<?> getOnPositionAndTeamId7DSL();

    List<?>getOnHeightAndWeightDSL();

    List<?>getOnPositionAndTeamId10DSL();

    List<?> getOnCountAll (Pageable pageable);

    List<?> getOnPositionAndTeamId20DSL();

    List<?> getOnPositionAndTeamId21(Pageable pageable);

    List<?>getOnPositionAndTeamId2DSL();

    List<?>getOnPositionAndTeamIdAndHeightDSL();


    Long countAllPlayers();
}
