package com.erichgamma.api.player.controller;

import com.erichgamma.api.player.model.PlayerDto;
import com.erichgamma.api.player.repository.PlayerRepository;
import com.erichgamma.api.player.service.PlayerService;
import com.erichgamma.api.player.service.PlayerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PlayerRouter {
    private  final PlayerRepository playerRepository;
    private  final PlayerService playerService;

    public  List<?> execute(String q ){
        return  switch (q)  {
            case "2" -> playerRepository.getOnPosition();
            case "3" -> playerRepository.getOnPositionNotNull();
            case "4" -> playerRepository.getOnPositionAndTeamId();
            case "4-1" -> playerRepository.getOnPositionAndTeamId2();
            case "5" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndTeamIdAndHeight()).stream().filter(i->Integer.parseInt((String)i.get("height")) >=170).toList();
            case "5-1" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndTeamIdAndHeight2()).stream().filter(i->Integer.parseInt((String)i.get("height")) >=170 ).toList();
            case "6" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndHeightAndTeamId()).stream().filter(i->Integer.parseInt((String)i.get("height")) >= 170).filter(i->Integer.parseInt((String)i.get("height")) <=180 ).toList();
            case "7" -> playerRepository.getPositionAndeRegion();
            case "8" -> playerRepository.getOnPositionAndTeamId7();
            case "9" -> playerRepository.getOnHeightAndWeight();
            case "10" -> playerRepository.getOnPositionAndTeamId10();
            case "18" -> playerService.getOnCountAll();
            case "20" -> playerRepository.getOnPositionAndTeamId20();
            case "21" -> playerService.getOnPositionAndTeamId21();
            case "22" -> playerRepository.getHeightAndTeamId();
            case "findAll" -> playerRepository.getAllPlayer();


            default -> throw new IllegalStateException("Unexpected value: " + q);
        };
    }

}
