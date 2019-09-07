package game.curio.web;

import java.io.IOException;
import java.text.ParseException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import game.curio.web.rest.dto.JsonGame;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
@Named // enable injection in EL
@RequestScoped
public class CurioSearch {

	private static final Logger LOG = LogManager.getLogger(CurioSearch.class);

	@Inject
	private SteamGameSearch steamGameSearch;

	private String input;

	private String output;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public void updateOutput() {
		if (StringUtils.isBlank(input)) {
			output = "Please enter a game title to search";
			return;
		}

		try {
			JsonGame jsonGame = steamGameSearch.searchGameByTitle(input);
			output = jsonGame.toString();
		}
		catch (IOException | ParseException e) {
			LOG.error("ERROR: {}", e.getMessage());
			output = "cannot search game with title: " + input;
		}
	}
}
