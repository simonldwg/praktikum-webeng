package webeng03.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import webeng03.models.Profile;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> authorizedUsers = List.of("test", "abc"); 

    /**
     * Default constructor. 
     */
    public Auth() {
        
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String username = request.getParameter("name");
		final HttpSession session = request.getSession();

		if(username == null || username.isEmpty()) {
			if(session.getAttribute("profile") != null) {
				session.invalidate();
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No username provided");
			}
			return;
		}

		if(!authorizedUsers.contains(username)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Could not find user with the provided name");
			return;
		}

		// Login-Logik
		final Cookie[] cookies = request.getCookies();
		int loginCounter = 0;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("loginCounter")) {
				try {
					loginCounter = Integer.parseInt(cookie.getValue());
				} catch (NumberFormatException e) {
					loginCounter = 0;
				}
			}
		}

		session.setAttribute("profile", new Profile(username, ++loginCounter));

		final Cookie loginCounterCookie = new Cookie("loginCounter", Integer.toString(loginCounter));
		loginCounterCookie.setMaxAge(60*60*24*365*10);
		response.addCookie(loginCounterCookie);

		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Profile"));


	}
	
	public Optional<String> readCookie(Cookie[] cookies, String key) {
	    return Arrays.stream(cookies)
	      .filter(c -> key.equals(c.getName()))
	      .map(Cookie::getValue)
	      .findAny();
	}
	
	

}
