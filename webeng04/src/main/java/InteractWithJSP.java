import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/InteractWithJSP")
public class InteractWithJSP extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        user.setFirstName("Max");
        user.setLastName("Mustermann");
        user.setAge(98);

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/user.jsp").include(request, response);


    }
}
