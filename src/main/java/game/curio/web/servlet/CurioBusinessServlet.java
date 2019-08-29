package game.curio.web.servlet;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

import game.curio.business.GameService;

/**
 * @author AdNovum Informatik AG
 */
public abstract class CurioBusinessServlet extends HttpServlet {

	@Inject
	protected GameService gameService;

}
