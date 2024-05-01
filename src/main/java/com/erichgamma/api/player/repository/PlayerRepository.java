package com.erichgamma.api.player.repository;

import com.erichgamma.api.player.model.PlayerDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erichgamma.api.player.model.Player;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> , PlayerJpqlRepository{




}
