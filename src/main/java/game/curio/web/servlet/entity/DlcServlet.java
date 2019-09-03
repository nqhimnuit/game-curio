package game.curio.web.servlet.entity;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.curio.business.DlcService;
import game.curio.business.GameService;
import game.curio.entities.DlcEntity;
import game.curio.web.servlet.CurioBusinessServlet;

/**
 * @author AdNovum Informatik AG
 */
public class DlcServlet extends CurioBusinessServlet {

	@Inject
	private DlcService dlcService;

	@Inject
	private GameService gameService;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		res.getWriter().println(dlcService.getDlcById(id));
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long gameId = Long.valueOf(req.getParameter("gameId"));
		String name = req.getParameter("name");
		Double price = Double.valueOf(req.getParameter("price"));
		dlcService.insertDlc(gameId, name, price);
		res.getWriter().println("Dlc inserted!");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		Long gameId = Long.valueOf(req.getParameter("gameId"));
		String name = req.getParameter("name");
		Double price = Double.valueOf(req.getParameter("price"));

		DlcEntity dlc = dlcService.getDlcById(id);
		dlc.setGame(gameService.getGameById(gameId));
		dlc.setName(name);
		dlc.setPrice(price);

		dlcService.updateEntity();
		res.getWriter().println("Dlc updated!");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		dlcService.deleteDlc(id);
		res.getWriter().println("Dlc deleted!");
	}

}
