package webeng03.servlets;



import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webeng03.models.Profile;

/**
 * Servlet implementation class Header
 */
@WebServlet("/WEB-INF/Header")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HeaderServlet() {      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Profile p = (session != null) ? (Profile)session.getAttribute("profile") : null;
		
		String content = ""
			+ "<header>"		
				+ "<h1>Webeng - Praktikum 2</h1>\r\n"
				+ "<ul> \r\n"
				+ 	"<li><a href=\"" + request.getContextPath()  + "\"/>Home</a></li>";
				if(p!= null)
				{
					
					content += "<li><a href=\"" + request.getContextPath()  + "/Profile\">Profile</a></li>";
					content += "<li><form name=\"login\" action=\"Auth\" method=\"POST\">"
								+ "<input type=\"submit\" value=\"Logout\" name=\"logout\" />"
								+ "</form>"
							+ "</li>";
				}
				else
				{
					content += "<li>"
								+ "<form name=\"login\" action=\"Auth\" method= \"POST\">"
								+ "<input type=\"text\" name=\"name\" value=\"\" />"
								+ "<input type=\"submit\" value=\"Login\" name=\"login\" />"
								+ "</form>"
							+ "</li>";
				}
		content += "" 
				+ "</ul>\r\n"
			+"</header>";
		response.getWriter().append(content);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
