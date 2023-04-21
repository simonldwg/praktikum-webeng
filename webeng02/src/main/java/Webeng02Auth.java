

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Base64;
import java.util.List;

/**
 * Servlet implementation class Webeng02
 */
@WebServlet("/Webeng02Auth")
public class Webeng02Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> authorizedUsers = List.of("test:123", "simon:abc");
	
    /**
     * Default constructor. 
     */
    public Webeng02Auth() {
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		String auth = request.getHeader("Authorization");
		System.out.println(auth);
        // 
        if (!authorize(auth)) {
            
        	response.setHeader("WWW-Authenticate", "BASIC realm=\"webeng\"");
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
          
        } else {
        	response.getWriter().append("Served at: ").append(request.getContextPath());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Boolean authorize(String auth)
	{
		if (auth == null) {
            return false;  // no auth
        }
        if (!auth.toLowerCase().startsWith("basic ")) { 
            return false;  // we only do BASIC
        }
       
        String base64Auth = auth.substring(6);
        // Decode it, using any base 64 decoder
        Base64.Decoder decoder = Base64.getDecoder();
        
        String decodedAuth = new String(decoder.decode(base64Auth));
		System.out.println(decodedAuth);
     
        // Check our user list to see if that user and password are "allowed"
        if (authorizedUsers.contains(decodedAuth)) {
            return true;
        } else {
            return false;
        }
		
	}

}
