package webeng03.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

	}
	
	public Optional<String> readCookie(Cookie[] cookies, String key) {
	    return Arrays.stream(cookies)
	      .filter(c -> key.equals(c.getName()))
	      .map(Cookie::getValue)
	      .findAny();
	}
	
	

}
