package com.erichgamma.api.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {
    private final HomeRouter homeRouter;
    
    @GetMapping("/")
    public String hello(){
        return "Welcome";
    }

    @GetMapping("/search")
    public ResponseEntity<List<?>> search(
        @RequestParam(value = "c", required = true) String category,
        @RequestParam(value = "q", required = true) String q,
        Pageable pageable
    ){
        // log.info("MY-INFO : Controller searchPlayer q is {}", q);
        // log.info("MY-INFO : Controller searchPlayer page is {}", pageable.getPageNumber());
        // log.info("MY-INFO : Controller searchPlayer limit is {}", pageable.getPageSize());
        // log.info("MY-INFO : Controller searchPlayer sortField is {}", pageable.getSort().toString());

        // nowPage, rowCount, pageSize, blockSize 외부주입.. count, size 1 부터, number 는 0부터

        return ResponseEntity.ok(homeRouter.execute(category, q, pageable));
    }
}