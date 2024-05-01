package com.erichgamma.api.player.controller;

import com.erichgamma.api.player.model.Player;
import com.erichgamma.api.player.model.PlayerDto;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erichgamma.api.player.service.PlayerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
    @ApiResponse(responseCode = "404", description = "Customer not found")})
@RestController  //controller + ResponseBody
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
@Slf4j
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerRouter playerRouter;


//    @GetMapping("2")
//    public ResponseEntity<List<?>> getOnPosition() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPosition());
//
//    }
//
//    @GetMapping("/3")
//    public ResponseEntity<List<?>> getOnPositionNotNull() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionNotNull());
//    }
//
//    @GetMapping("/4")
//    public ResponseEntity<List<?>> getOnPositionAndTeamId() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamId());
//    }
//
//    @GetMapping("/4-1")
//    public ResponseEntity<List<?>> getOnPositionAndTeamId2() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamId2());
//    }
//
//    @GetMapping("/5")
//    public ResponseEntity<List<?>> getOnPositionAndTeamIdAndHeight() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamIdAndHeight());
//    }
//
//    @GetMapping("/5-1")
//    public ResponseEntity<List<?>> getOnPositionAndTeamIdAndHeight2() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamIdAndHeight2());
//    }
//
//    @GetMapping("/6")
//    public ResponseEntity<List<?>> getOnPositionAndHeightAndTeamId() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndHeightAndTeamId());
//    }
//
//    @GetMapping("/8")
//    public ResponseEntity<List<?>> getOnPositionAndTeamId7() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamId7());
//    }
//
//    @GetMapping("/9")
//    public ResponseEntity<List<?>> getOnHeightAndWeight() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnHeightAndWeight());
//    }
//
//    @GetMapping("/10")
//    public ResponseEntity<List<?>> getOnPositionAndTeamId10() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamId10());
//    }
//
//    @GetMapping("/18")
//    public ResponseEntity<List<?>> getOnCountAll() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnCountAll());
//    }
//
//    @GetMapping("/20")
//    public ResponseEntity<List<?>> getOnPositionAndTeamId20() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamId20());
//    }
//    @GetMapping("/21")
//    public ResponseEntity<List<?>> getOnPositionAndTeamId21() throws SQLException {
//        return ResponseEntity.ok(playerService.getOnPositionAndTeamId21());
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchPlayer(
        @RequestParam(value = "q", required = true) String q,
        @RequestParam(value = "playerName", required = false) String playerName,
        @RequestParam(value = "position", required = false) String position,
        @RequestParam(value = "teamId", required = false) String teamId,
        @RequestParam(value = "playerId", required = false)String playerId
    ){
        log.info("controller searchPlayer q is {} " , q);
        List <Map<String,Object>>o = playerRouter.execute(q);
        return ResponseEntity.ok(o);
    }





}
