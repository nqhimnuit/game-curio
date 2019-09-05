package game.curio.web.rest;

import static javax.ws.rs.core.Response.Status.OK;

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

	private static final String STEAM_SEARCH_API = "https://store.steampowered.com/search";

	private static final String STEAM_QUERY = "term";

	// https://store.steampowered.com/api/appdetails?appids=262060

	@GET
	@Path("/search/{gameTitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchGame(@PathParam("gameTitle") String gameTitle) {
		String htmlResult = getSteamSearchResultHtml(gameTitle);
		List<String> appIds = getAppIds(htmlResult);
		return Response.status(OK).entity(appIds).build();
	}

	private String getSteamSearchResultHtml(String gameTitle) {
		Client client = ClientBuilder.newClient();
		WebTarget steamTarget = client.target(STEAM_SEARCH_API).queryParam(STEAM_QUERY, gameTitle);
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

}
