package game.curio.web;

import static java.lang.String.format;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

/**
 * @author AdNovum Informatik AG
 */
@Named // enable injection in EL
@RequestScoped
public class CurioSearch {

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
		if (input.equalsIgnoreCase("darkest dungeon")) {
			output = "You searched for \"Darkest Dungeon\", which is correct!";
		}
		else {
			output = format("You searched for '%s' which is the wrong game, try again!", input);
		}
	}
}
