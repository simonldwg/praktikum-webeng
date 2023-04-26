package webeng03.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webeng03.models.Profile;


/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProfileServlet() {
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();  
        Profile p = (Profile)session.getAttribute("profile");
        if(p==null)
        	response.sendRedirect(request.getContextPath()+"/");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Header");

		String content = ""
				+ "<!doctype html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
					+ "<title>Webeng03</title>\r\n"
					+ "<meta name=\"description\" content=\"Webeng Praktikum 3\">\r\n"
					+ "<meta name=\"keywords\" content=\"webeng\">\r\n"	
				+ "</head>\r\n"
				+ "<body>\r\n";

		response.getWriter().append(content);

		dispatcher.include(request, response);
		content =		"<main>"
						+ "<h1>Profil</h1>"
						+ "name: " + p.getName() + "</br>"
						+ "LoginCount: " + String.valueOf(p.getLoginCounter()) + "</br>"
					+ "</main>"	
				+ "</body>\r\n"
				+ "</html>";
			
			response.getWriter().append(content);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		// 
		doGet(request, response);
	}

}
