package game.curio.web.rest;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

import java.io.IOException;
import java.text.ParseException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import game.curio.web.SteamGameSearch;
import game.curio.web.rest.dto.JsonGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
@Path("steam")
public class SteamGameResource {

	private static final Logger LOG = LogManager.getLogger(SteamGameResource.class);

	@Inject
	private SteamGameSearch steamGameSearchImpl;

	@GET
	@Path("/search/{gameTitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchGame(@PathParam("gameTitle") String gameTitle) {
		try {
			JsonGame game = steamGameSearchImpl.searchGameByTitle(gameTitle);
			return Response.status(OK).entity(game).build();
		}
		catch (IOException | ParseException e) {
			LOG.error("ERROR: {}", e.getMessage());
			return Response.status(NOT_FOUND).build();
		}
	}



}
