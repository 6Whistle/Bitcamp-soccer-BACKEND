package com.erichgamma.api.player.controller;

import com.erichgamma.api.common.model.Box;
import com.erichgamma.api.common.model.PageDTO;
import com.erichgamma.api.common.service.PageService;
import com.erichgamma.api.player.repository.PlayerRepository;
import com.erichgamma.api.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import javax.sound.sampled.BooleanControl;
import java.util.List;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@Component
public class PlayerRouter {
    private  final PlayerRepository playerRepository;
    private  final PlayerService playerService;
    private  final PageService pageService;

    public  List<?> execute(String q , Pageable pageable){
        log.info("MY-INFO : Controller searchPlayer q is {}", q);
        log.info("MY-INFO : Controller searchPlayer page is {}", pageable.getPageNumber());
        log.info("MY-INFO : Controller searchPlayer limit is {}", pageable.getPageSize());
        log.info("MY-INFO : Controller searchPlayer sortField is {}", pageable.getSort().toString());

        // nowPage, rowCount, pageSize, blockSize 외부주입.. count, size 1 부터, number 는 0부터



        Box.builder()
                .pageDTO(pageService.getPageDTO((long) pageable.getPageNumber(), playerRepository.countAllPlayers(), (long) pageable.getPageSize()))
                .list(playerRepository.getAllPlayer())
                .build();






        return  switch (q)  {
            case "2" -> playerRepository.getOnPosition();
            case "2-dsl" -> playerRepository.getOnPositionDSL();
            case "3" -> playerRepository.getOnPositionNotNull();
            case "3-dsl" -> playerRepository.getOnPositionNotNullDSL();
            case "4" -> playerRepository.getOnPositionAndTeamId();
            case "4-dsl" -> playerRepository.getOnPositionAndTeamIdDSL();
            case "4-1" -> playerRepository.getOnPositionAndTeamId2();
            case "4-1-dsl" -> playerRepository.getOnPositionAndTeamId2DSL();
            case "5" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndTeamIdAndHeight()).stream().filter(i->Integer.parseInt((String)i.get("height")) >=170).toList();
            case "5-dsl" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndTeamIdAndHeightDSL()).stream().filter(i->Integer.parseInt((String)i.get("height")) >=170).toList();
            case "5-1" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndTeamIdAndHeight2()).stream().filter(i->Integer.parseInt((String)i.get("height")) >=170 ).toList();
            case "6" -> ((List<Map<String, Object>>)playerRepository.getOnPositionAndHeightAndTeamId()).stream().filter(i->Integer.parseInt((String)i.get("height")) >= 170).filter(i->Integer.parseInt((String)i.get("height")) <=180 ).toList();
            case "7" -> playerRepository.getPositionAndeRegion();
            case "8" -> playerRepository.getOnPositionAndTeamId7();
            case "8-dsl" -> playerRepository.getOnPositionAndTeamId7DSL();
            case "9" -> playerRepository.getOnHeightAndWeight();
            case "10" -> playerRepository.getOnPositionAndTeamId10();
            case "10-dsl" -> playerRepository.getOnPositionAndTeamId10DSL();
            case "18" -> playerService.getOnCountAll();
            case "20" -> playerRepository.getOnPositionAndTeamId20();
            case "20-dsl" -> playerRepository.getOnPositionAndTeamId20DSL();
            case "21" -> playerService.getOnPositionAndTeamId21();
            case "22" -> playerRepository.getHeightAndTeamId();
            case "findAll" -> playerRepository.getAllPlayer();

            default -> throw new IllegalStateException("Unexpected value: " + q);
        };
    }

}
