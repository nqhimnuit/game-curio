package game.curio.web.servlet.entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.curio.entities.GameEntity;
import game.curio.web.servlet.CurioBusinessServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
public class GameServlet extends CurioBusinessServlet {

	private static final Logger LOG = LogManager.getLogger(GameServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String charset = StringUtils.defaultIfBlank(req.getHeader("accept-charset"), "utf-8");
		res.setContentType("text/html; charset=" + charset);

		Long id = Long.valueOf(req.getParameter("id"));
		PrintWriter writer = res.getWriter();
		writer.println(gameService.getGameById(id).toString());
		writer.println("user agent: " + req.getHeader("user-agent"));
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		gameService.deleteGame(id);
		res.getWriter().write("Game deleted!!\n");
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		Double price = Double.valueOf(req.getParameter("price"));
		gameService.insertGame(title, new Date(), description, price);

		res.getWriter().write("Game created!!\n");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		LOG.info("request params = {}", req.getParameterMap());
		Double price = Double.valueOf(req.getParameter("price"));
		//Date releaseDate = req.getParameter("release_date");

		GameEntity game = gameService.getGameById(id);
		LOG.info("req.getParameter(\"price\") = {}", req.getParameter("price"));

		game.setTitle(title);
		game.setDescription(description);
		game.setPrice(price);

		gameService.updateEntity();

		res.getWriter().write("Game updated!!\n");
	}
}
