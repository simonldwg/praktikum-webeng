package webeng03.servlets;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String content = ""
			+ "<!doctype html>\r\n"
			+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<title>Webeng03</title>\r\n"
				+ "<meta name=\"description\" content=\"Webeng Praktikum 3\">\r\n"
				+ "<meta name=\"keywords\" content=\"webeng\">\r\n"
				+ "</head>\r\n"
			+ "<body>\r\n"
				+ "<main>"
					+ "<h1>Willkommen</h1>"
					+ "Dies ist eine Webanwendung"
				+ "</main>"
			+ "</body>\r\n"
			+ "</html>";
		
		response.getWriter().append(content);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
