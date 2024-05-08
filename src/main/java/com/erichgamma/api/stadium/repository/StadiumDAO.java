package com.erichgamma.api.stadium.repository;

import java.util.List;

public interface StadiumDAO {

    List<?> getAllStadiumsDSL();
    
    List<?> stadiumNameWithTeamDSL();
    List<?> stadiumAndTeamAndScheduleDSL();

    List<?> pohangSteelersGkDSL();
    List<?> homeTeamWinDSL();
    List<?> noHomeTeamDSL();

    Long countAllStadiums();
}
