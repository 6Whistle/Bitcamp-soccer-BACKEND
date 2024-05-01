package com.erichgamma.api.stadium.controller;

import com.erichgamma.api.common.component.pagination.PageRequestVo;
import com.erichgamma.api.stadium.model.Stadium;
import com.erichgamma.api.stadium.model.StadiumDto;
import com.erichgamma.api.stadium.service.StadiumService;
import com.erichgamma.api.stadium.service.StadiumServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping(path = "/api/stadium")
public class StadiumController {
    private final StadiumService stadiumService;
}
