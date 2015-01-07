package com.jebrial.thumpweb.db;

import com.jebrial.thumpweb.core.Player;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class PlayerDAO extends AbstractDAO<Player> {
    public PlayerDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Player> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Player create(Player player) {
        return persist(player);
    }

    public List<Player> findAll() {
        return list(namedQuery("com.jebrial.thumpweb.core.Player.findAll"));
    }

}
