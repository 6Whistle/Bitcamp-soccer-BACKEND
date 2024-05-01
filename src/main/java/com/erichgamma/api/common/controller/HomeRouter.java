package com.erichgamma.api.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.erichgamma.api.team.controller.TeamRouter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HomeRouter {
    private final TeamRouter teamRouter;

    public List<Map<String, Object>> execute(String category, String q){
        return switch (category) {
            case "team" -> teamRouter.execute(q);
            default -> List.of();
        };
    }
}
