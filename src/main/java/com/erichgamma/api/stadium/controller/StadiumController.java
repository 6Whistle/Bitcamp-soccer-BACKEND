package com.erichgamma.api.stadium.controller;

import com.erichgamma.api.common.model.Box;
import com.erichgamma.api.common.service.PageService;
import com.erichgamma.api.stadium.service.StadiumService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final StadiumRouter stadiumRouter;
    private final PageService pageService;

    @GetMapping("/search")
    public ResponseEntity<?> contAllStadiums(
            @RequestParam(value = "c", required = true) String category,
            @RequestParam(value = "q", required = true) String q,
            Pageable pageable
    ) {
        log.info("MY-INFO : Controller searchStadium q is {}", q);
        log.info("MY-INFO : Controller searchStadium page is {}", pageable.getPageNumber());
        log.info("MY-INFO : Controller searchStadium limit is {}", pageable.getPageSize());
        log.info("MY-INFO : Controller searchStadium sortField is {}", pageable.getSort().toString());
        // nowPage, rowCount, pageSize, blockSize 외부주입... count, size 1 부터, number 는 0부터

//        Box box = Box
//                .builder()
//                .pageDto(pageService.getPageDto(pageable))
//                .list(stadiumRouter.execute(q))
//                .build();

        return ResponseEntity.ok(null);
    }
}


