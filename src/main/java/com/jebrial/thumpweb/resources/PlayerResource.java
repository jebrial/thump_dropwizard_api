package com.jebrial.thumpweb.resources;

import com.jebrial.thumpweb.core.Player;
import com.jebrial.thumpweb.db.PlayerDAO;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
//import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/player/{playerId}")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    private final PlayerDAO playersDAO;

    public PlayerResource(PlayerDAO playersDAO) {
        this.playersDAO = playersDAO;
    }

    @GET
    @UnitOfWork
    public Player getPlayer(@PathParam("playerId") LongParam playerId) {
        return findSafely(playerId.get());
    }

    private Player findSafely(long playerId) {
        final Optional<Player> player = playersDAO.findById(playerId);
        if (!player.isPresent()) {
            //throw new NotFoundException("No such user.");
        }
        return player.get();
    }

}
