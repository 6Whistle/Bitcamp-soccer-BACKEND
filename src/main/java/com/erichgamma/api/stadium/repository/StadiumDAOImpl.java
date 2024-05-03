package com.erichgamma.api.stadium.repository;

import com.erichgamma.api.stadium.model.QStadium;
import com.erichgamma.api.stadium.model.StadiumDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class StadiumDAOImpl implements StadiumDAO {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StadiumDto> getAllStadium() {
        return queryFactory.select(
                        QStadium.stadium.id,
                        QStadium.stadium.stadiumId,
                        QStadium.stadium.stadiumName,
                        QStadium.stadium.hometeamId,
                        QStadium.stadium.seatCount,
                        QStadium.stadium.address,
                        QStadium.stadium.ddd,
                        QStadium.stadium.tel)
                .from(QStadium.stadium)
                .fetch()
                .stream().map(tuple -> StadiumDto.builder()
                        .id(tuple.get(QStadium.stadium.id))
                        .stadiumId(tuple.get(QStadium.stadium.stadiumId))
                        .stadiumName(tuple.get(QStadium.stadium.stadiumName))
                        .hometeamId(tuple.get(QStadium.stadium.hometeamId))
                        .seatCount(tuple.get(QStadium.stadium.seatCount))
                        .address(tuple.get(QStadium.stadium.address))
                        .ddd(tuple.get(QStadium.stadium.ddd))
                        .tel(tuple.get(QStadium.stadium.tel))
                        .build())
                .toList()
                ;
    }

}
