package game.curio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @author AdNovum Informatik AG
 */
@WebServlet(urlPatterns = "/curio/annotation", initParams = {
		@WebInitParam(name = "CurioInitParamAnnotation", value = "This is init param from @")
})
public class CurioServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		handleGetRequest(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		handlePostRequest(req, res);
	}

	private void handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter writer = res.getWriter();
		writer.write("Hello game curio");
		writer.write("\n");
		writer.write("The init param from web.xml is: "
				+ getInitParameter("CurioInitParamWeb"));
		writer.write("\n");
		writer.write("The init param from annotation is: "
				+ getInitParameter("CurioInitParamAnnotation"));
		writer.write("\n");

		String reqParam = req.getParameter("ask-for");
		if (StringUtils.isBlank(reqParam)) {
			writer.write("You ask for Nothing!");
		}
		else {
			writer.write("You ask for " + reqParam);
		}
		writer.write("\n");
	}

	private void handlePostRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		PrintWriter writer = res.getWriter();
		writer.write("this is the payload in request:\n");
		String data;
		while ((data = reader.readLine()) != null) {
			writer.write(data);
			writer.write("\n");
		}
	}
}
