package game.curio.web.rest;

import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
@Path("steam")
public class SteamGameSearch {

	private static final Logger LOG = LogManager.getLogger(SteamGameSearch.class);

	//private static final String STEAM_SEARCH_API = "https://store.steampowered.com/search/?term=";
	private static final String STEAM_STORE_API = "https://store.steampowered.com/api/";
	private static final String CURIO_API = "http://localhost:8080/game-curio/rest/games";
	// https://store.steampowered.com/api/appdetails?appids=262060

	@GET
	@Path("/search/{gameTitle}") // http://localhost:8080/game-curio/rest/games/3/game
	public Response searchGame(@PathParam("gameTitle") String gameTitle) {
		LOG.info("enterring searchGame with param = {}", gameTitle);
		Client steamClient = ClientBuilder.newClient();
		//WebTarget target = steamClient.target(CURIO_API).path("{id}").resolveTemplate("id", 3).path("game");

		WebTarget steamTarget = steamClient.target(STEAM_STORE_API).path("appdetails").queryParam("appids", "262060");
		LOG.info("steam target uri = {}", steamTarget.getUri().toString());

		String response = steamTarget.request(MediaType.APPLICATION_JSON).get(String.class);

		return Response
				.status(OK)
				.entity("you requested: " + gameTitle + "\n\nAnd we response: " + response)
				.build();
	}

}
