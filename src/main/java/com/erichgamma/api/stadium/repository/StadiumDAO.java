package com.erichgamma.api.stadium.repository;

import java.util.List;
import java.util.Map;

public interface StadiumDAO {

    List<?> getAllStadiumsDSL();
    
    List<?> stadiumNameWithTeamDSL();
    List<?> stadiumAndTeamAndScheduleDSL();

    List<?> pohangSteelersGkDSL();
    List<?> homeTeamWinDSL();
    List<?> noHomeTeamDSL();
}
