package game.curio.web.servlet.entity;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.curio.entities.GameEntity;
import game.curio.web.servlet.CurioBusinessServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 * tested with cURL:
 * $ curl -X GET http://localhost:8080/game-curio/get-game
 * $ curl -X DELETE 'http://localhost:8080/game-curio/delete-game?id=8'
 * $ curl -X PUT 'http://localhost:8080/game-curio/insert-game?title=Batminh&description=this+is
 * +a+game+of+minh&price=1000'
 * $ curl -X POST 'http://localhost:8080/game-curio/update-game?id=6&title=My+Game&description=my
 * +description&price=888.8'
 */
public class GameServlet extends CurioBusinessServlet {

	private static final Logger LOG = LogManager.getLogger(GameServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		res.getWriter().write(gameService.getGameById(id).toString() + "\n");
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
