package com.jebrial.thumpweb.resources;

import com.jebrial.thumpweb.core.Player;
import com.jebrial.thumpweb.db.PlayerDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
public class PlayersResource {

    private final PlayerDAO playersDAO;

    public PlayersResource(PlayerDAO playersDAO) {
        this.playersDAO = playersDAO;
    }

    @POST
    @UnitOfWork
    public Player createPlayer(Player player) {
        return playersDAO.create(player);
    }

    @GET
    @UnitOfWork
    public List<Player> listPlayers() {
        return playersDAO.findAll();
    }

}
