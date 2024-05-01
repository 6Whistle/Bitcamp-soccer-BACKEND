package com.erichgamma.api.schedule.controller;

import com.erichgamma.api.schedule.service.ScheduleService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Slf4j
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleRouter Schedulerouter;

    @GetMapping("/22")
    public ResponseEntity<List<Map<String, Object>>> getScheduleByDate(
            @RequestParam(value = "q,", required = true) String q,
            @RequestParam(value = "scheDate", required = false) String scheDate,
            @RequestParam(value = "gubun,", required = false) String gubun,
            @RequestParam(value = "hometeamId,", required = false) String hometeamId,
            @RequestParam(value = "hometeamId,", required = false) Integer homeScore,
            @RequestParam(value = "awayteamId,", required = false) String awayteamId,
            @RequestParam(value = "awayScore,", required = false) Integer awayScore ){

        List<Map<String, Object>> o = Schedulerouter.execute(q);

        return ResponseEntity.ok(o);
    }




}


