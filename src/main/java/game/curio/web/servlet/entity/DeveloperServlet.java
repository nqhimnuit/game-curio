package game.curio.web.servlet.entity;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.curio.business.DeveloperService;
import game.curio.entities.DeveloperEntity;
import game.curio.web.servlet.CurioBusinessServlet;

/**
 * @author AdNovum Informatik AG
 */
public class DeveloperServlet extends CurioBusinessServlet {

	@Inject
	private DeveloperService developerService;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		DeveloperEntity developer = developerService.getDeveloperById(id);
		res.getWriter().println(developer.toString());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		DeveloperEntity dev = developerService.getDeveloperById(id);
		dev.setName(req.getParameter("name"));
		developerService.updateEntity();
		res.getWriter().println("updated developer: " + dev.toString());
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = developerService.insertDeveloper(req.getParameter("name"));
		res.getWriter().println("inserted developer with id = " + id);
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		developerService.deleteDeveloper(id);
		res.getWriter().println("deleted developer with id = " + id);
	}

}
