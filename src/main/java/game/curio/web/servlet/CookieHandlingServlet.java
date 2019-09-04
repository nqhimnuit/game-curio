package game.curio.web.servlet;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AdNovum Informatik AG
 */
public class CookieHandlingServlet extends HttpServlet {
	private static final String COOKIE_NAME_VISIT = "visit";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie[] cookies = req.getCookies();
		Cookie visit = null;
		if (cookies == null) { // first time visit
			visit = new Cookie(COOKIE_NAME_VISIT, "000");
			addCookieToResponse(res, visit);
		}
		else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_NAME_VISIT)) {
					int count = Integer.parseInt(cookie.getValue());
					visit = new Cookie(cookie.getName(), String.valueOf(count + 1));
					addCookieToResponse(res, visit);
				}
			}
		}

		if (visit != null) {
			res.getWriter().println("cookie: " + visit.getName() + ": " + visit.getValue());
		}
	}

	private void addCookieToResponse(HttpServletResponse res, Cookie cookie) {
		cookie.setMaxAge(30);
		res.addCookie(cookie);
	}
}
