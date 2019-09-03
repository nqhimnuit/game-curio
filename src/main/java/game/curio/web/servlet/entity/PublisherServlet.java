package game.curio.web.servlet.entity;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import game.curio.business.PublisherService;
import game.curio.entities.PublisherEntity;
import game.curio.web.servlet.CurioBusinessServlet;

/**
 * @author AdNovum Informatik AG
 */
public class PublisherServlet extends CurioBusinessServlet {

	@Inject
	private PublisherService publisherService;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		res.getWriter().println(publisherService.getPublisherById(id).toString());
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		publisherService.insertPublisher(req.getParameter("name"));
		res.getWriter().println("Publisher created!");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		PublisherEntity publisher = publisherService.getPublisherById(id);
		publisher.setName(req.getParameter("name"));
		publisherService.updateEntity();
		res.getWriter().println("Publisher updated!");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		publisherService.deletePublisher(id);
		res.getWriter().println("Publisher deleted!");
	}

}
