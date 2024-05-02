package com.erichgamma.api.player.repository;

import com.erichgamma.api.player.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@PersistenceContext
public class PlayerDAOlmpl implements PlayerDAO {


    private final EntityManager entitymanager;





//    @Override
//    public void insert(Player player) {
//        entitymanager.persist(player);
//
//    }
//
//    @Override
//    public void update(Player player) {
//        entitymanager.persist(player);
//
//    }
}
