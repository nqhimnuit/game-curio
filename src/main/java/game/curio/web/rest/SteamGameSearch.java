package game.curio.web.rest;

import static javax.ws.rs.core.Response.Status.OK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.curio.web.rest.dto.JsonGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author AdNovum Informatik AG
 */
@Path("steam")
public class SteamGameSearch {

	private static final Logger LOG = LogManager.getLogger(SteamGameSearch.class);

	private static final String SEARCH_URI = "https://store.steampowered.com/search";

	private static final String GAME_TITLE_QUERY = "term";

	private static final String GAME_DETAILS_URI = "https://store.steampowered.com/api/appdetails";

	private static final String APP_ID_QUERY = "appids";

	private static final String ROOT_NODE = "data";

	private static final String NAME_NODE = "name";

	private static final String DESCRIPTION_NODE = "short_description";

	private static final String PRICE_ROOT_NODE = "price_overview";

	private static final String FINAL_PRICE_NODE = "final";

	private static final String DATE_FORMAT = "dd MMM, yyyy";

	private static final String RELEASE_DATE_ROOT_NODE = "release_date";

	private static final String DATE_NODE = "date";

	@GET
	@Path("/search/{gameTitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchGame(@PathParam("gameTitle") String gameTitle) throws IOException {
		String htmlResult = getSteamSearchResultHtml(gameTitle);
		List<String> appIds = getAppIds(htmlResult);
		String gameDetails = getGameDetailsInfo(appIds.get(0));
		LOG.info("game details = {}", gameDetails);

		JsonGame game = deserialize(gameDetails);
		return Response.status(OK).entity(game).build();
	}

	private String getSteamSearchResultHtml(String gameTitle) {
		Client client = ClientBuilder.newClient();
		WebTarget steamTarget = client.target(SEARCH_URI).queryParam(GAME_TITLE_QUERY, gameTitle);
		LOG.info("steam target uri = {}", steamTarget.getUri().toString());
		return steamTarget.request(MediaType.TEXT_HTML).get(String.class);
	}

	private List<String> getAppIds(String htmlResult) {
		Document doc = Jsoup.parse(htmlResult, "UTF-8");
		Elements links = doc.getElementById("search_resultsRows").select("a");
		List<String> appIds = new ArrayList<>();
		for (Element link : links) {
			String attr = link.attr("data-ds-appid");
			if (!StringUtil.isBlank(attr)) {
				appIds.add(attr);
			}
		}
		return appIds;
	}

	private String getGameDetailsInfo(String appId) {
		Client client = ClientBuilder.newClient();
		WebTarget gameTarget = client.target(GAME_DETAILS_URI).queryParam(APP_ID_QUERY, appId);
		return gameTarget.request(MediaType.APPLICATION_JSON).get(String.class);
	}

	/**
	 * https://www.baeldung.com/jackson-nested-values
	 */
	private JsonGame deserialize(String jsonGame) throws IOException {
		JsonNode jsonNode = new ObjectMapper().readTree(jsonGame);
		JsonGame game = new JsonGame();
		JsonNode gameDataNode = jsonNode.get("262060").get(ROOT_NODE);
		game.setTitle(gameDataNode.get(NAME_NODE).textValue());
		game.setDescription(gameDataNode.get(DESCRIPTION_NODE).textValue());
		game.setPrice(gameDataNode.get(PRICE_ROOT_NODE).get(FINAL_PRICE_NODE).doubleValue());

		SimpleDateFormat sdt = new SimpleDateFormat(DATE_FORMAT);
		String releaseDate = gameDataNode.get(RELEASE_DATE_ROOT_NODE).get(DATE_NODE).textValue();
		try {
			game.setReleaseDate(sdt.parse(releaseDate));
		}
		catch (ParseException e) {
			LOG.error("ERROR: Cannot parse release date: {}", releaseDate);
		}
		return game;
	}

	@Deprecated
	private String simulateJsonGame() {
		try {
			File file = new File("/local/minh/projects/minh-local-project/game-curio/game.json");
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String s;
			StringBuilder jsonGameBuilder = new StringBuilder();
			while ((s = bf.readLine()) != null) {
				jsonGameBuilder.append(s);
			}
			return jsonGameBuilder.toString();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
