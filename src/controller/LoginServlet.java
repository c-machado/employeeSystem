import DAO.UserDAO;
import model.LoginMaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String password = request.getParameter("password");
        UserDAO loginService = new UserDAO();
        boolean result = loginService.authenticateUser(userId, password);
        LoginMaster user = loginService.getUserByUserId(userId);
        if(result == true){
            request.getSession().setAttribute("user", user);
            response.sendRedirect("login.jsp");
        }
        else{
            response.sendRedirect("error.jsp");
        }
    }

}
