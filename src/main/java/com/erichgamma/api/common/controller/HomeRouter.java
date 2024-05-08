package com.erichgamma.api.common.controller;

import java.util.List;
import java.util.Map;

import com.erichgamma.api.player.controller.PlayerRouter;
import com.erichgamma.api.schedule.controller.ScheduleRouter;
import com.erichgamma.api.stadium.controller.StadiumRouter;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.erichgamma.api.team.controller.TeamRouter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HomeRouter {
    private final TeamRouter teamRouter;
    private final PlayerRouter playerRouter;
    private final ScheduleRouter scheduleRouter;
    private final StadiumRouter stadiumRouter;

    public List<?> execute(String category, String q, Pageable pageable){
        return switch (category) {
            case "team" -> teamRouter.execute(q, pageable);
            case "player" -> playerRouter.execute(q,pageable);
            case "schedule" -> scheduleRouter.execute(q);
            case "stadium" -> stadiumRouter.execute(q);
            default -> List.of();
        };
    }
}
