package game.curio;

import static java.lang.String.format;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

/**
 * @author AdNovum Informatik AG
 */

@Named // enable injection in EL
@Stateless
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
		}
		if (input.equalsIgnoreCase("darkest dungeon")) {
			output = "You searched for \"Darkest Dungeon\", which is correct!";
		}
		else {
			output = format("You searched for '%s' which is the wrong game, try again!", input);
		}
	}
}
