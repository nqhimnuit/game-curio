package game.curio.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.curio.web.rest.dto.GameDto;
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
@RequestScoped
public class SteamGameSearch {

	private static final Logger LOG = LogManager.getLogger(SteamGameSearch.class);

	private static final String SEARCH_URI = "https://store.steampowered.com/search";

	private static final String GAME_TITLE_QUERY = "term";

	private static final String GAME_DETAILS_URI = "https://store.steampowered.com/api/appdetails";

	private static final String APP_ID_QUERY = "appids";

	private static final String ROOT_NODE = "data";

	private static final String DATE_FORMAT = "dd MMM, yyyy";

	private static final String RELEASE_DATE_ROOT_NODE = "release_date";

	private static final String DATE_NODE = "date";

	private static final String NAME_NODE = "name";

	private static final String DESCRIPTION_NODE = "short_description";

	private static final String PRICE_ROOT_NODE = "price_overview";

	private static final String FINAL_PRICE_NODE = "final";

	public GameDto searchGameByTitle(String title) throws IOException, ParseException {
		String htmlResult = getSteamSearchResultHtml(title);
		List<String> appIds = getAppIds(htmlResult);
		String appId = appIds.get(0);
		String gameDetails = getGameDetailsInfo(appId);
		return deserialize(gameDetails, appId);
	}

	private GameDto deserialize(String jsonGame, String gameId)
			throws IOException, ParseException {

		JsonNode jsonNode = new ObjectMapper().readTree(jsonGame);
		JsonNode gameDataNode = jsonNode.get(gameId).get(ROOT_NODE);
		GameDto game = setGameInfo(gameDataNode);

		SimpleDateFormat sdt = new SimpleDateFormat(DATE_FORMAT);
		String releaseDate = gameDataNode.get(RELEASE_DATE_ROOT_NODE).get(DATE_NODE).textValue();
		game.setReleaseDate(sdt.parse(releaseDate));
		return game;
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

	private GameDto setGameInfo(JsonNode gameDataNode) {
		GameDto game = new GameDto();
		game.setTitle(gameDataNode.get(NAME_NODE).textValue());
		game.setDescription(gameDataNode.get(DESCRIPTION_NODE).textValue());
		game.setPrice(gameDataNode.get(PRICE_ROOT_NODE).get(FINAL_PRICE_NODE).doubleValue());
		return game;
	}

}
