package com.erichgamma.api.player.repository;

import java.util.List;

public interface PlayerDAO {

    List<?> getAllPlayer();

    List<?> getOnPositionDSL();

    List<?> getOnPositionNotNullDSL();

    List<?> getOnPositionAndTeamIdDSL();

    List<?> getOnPositionAndTeamId7DSL();

    List<?>getOnHeightAndWeightDSL();

    List<?>getOnPositionAndTeamId10DSL();





}
