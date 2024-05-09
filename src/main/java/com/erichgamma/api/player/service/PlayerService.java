package com.erichgamma.api.player.service;


import com.erichgamma.api.common.command.CommandService;
import com.erichgamma.api.common.query.QueryService;
import com.erichgamma.api.player.model.PlayerDto;

import java.util.List;
import java.util.Map;

public interface PlayerService extends CommandService<PlayerDto>, QueryService<PlayerDto> {
    List<Map<String,Object>> getOnCountAll();
    List<Map<String,Object>> getOnPositionAndTeamId21();











}
