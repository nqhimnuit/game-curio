package game.curio;

import javax.ejb.Stateless;
import javax.inject.Named;

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
		this.output = "output updated!";
	}
}
