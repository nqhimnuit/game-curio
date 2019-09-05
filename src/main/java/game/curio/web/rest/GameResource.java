package game.curio.web.rest;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import game.curio.business.GameService;
import game.curio.entities.GameEntity;

/**
 * @author AdNovum Informatik AG
 */
@Path("/games")
public class GameResource {

	@Inject
	private GameService gameService;

	/**
	 * E.g. http://localhost:8080/game-curio/rest/games/3/game
	 */
	@GET
	@Path("/{id}/game")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGameById(@PathParam("id") long id) {
		GameEntity game = gameService.getGameById(id);
		return Response.ok().entity(game).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertGame(GameEntity game) {
		GameEntity newGame = gameService.insertGame(
				game.getTitle(), game.getReleaseDate(), game.getDescription(), game.getPrice());

		return Response.status(CREATED).entity(newGame).build();
	}

	@DELETE
	@Path("/{id}")
	public Response updateGame(@PathParam("id") long id) {
		gameService.deleteGame(id);
		return Response.status(OK).build();
	}

}
