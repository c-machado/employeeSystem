import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import model.Department;
import model.Employees;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "/"/*,urlPatterns = {"/Employees"}*/ )
public class ServletEmployees extends HttpServlet {

    //private static final long serialVersionUID = 1 L;

   private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    public void init() {
        employeeDAO = new EmployeeDAO();
        departmentDAO = new DepartmentDAO();
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doGet(request, response);
     }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String action = request.getServletPath();
            System.out.println("doGet");
         try {
             switch (action) {
                 case "/new":
                     showNewForm(request, response);
                     break;
                 case "/insert":
                     insertEmployee(request, response);
                     break;
                 case "/delete":
                     deleteEmployee(request, response);
                     break;
                 case "/edit":
                     showEditForm(request, response);
                     break;
                 case "/update":
                     updateEmployee(request, response);
                     break;
                 default:
                     System.out.println("default");
                     listEmployee(request, response);
                     break;
             }
         } catch (SQLException | ParseException ex) {
             throw new ServletException(ex);
         }
     }

     private void listEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ServletException {
         List< Employees > listEmployee = employeeDAO.getAllEmployees();
         request.setAttribute("listEmployee", listEmployee);
         System.out.println("ListEmployee "+listEmployee);

         RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
         dispatcher.forward(request, response);
     }

     private void showNewForm(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-form.jsp");
         dispatcher.forward(request, response);
     }

     private void showEditForm(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         Employees existingEmployee = employeeDAO.getUserById(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("Employee-form.jsp");
         request.setAttribute("Employee", existingEmployee);
         dispatcher.forward(request, response);

     }

     private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ParseException {
         String name = request.getParameter("name");
         String lastName = request.getParameter("lastName");
         Date dob = new SimpleDateFormat("dd/MM/yyyy").parse("dob");
         String email = request.getParameter("email");
         int deptid = Integer.parseInt(request.getParameter("id"));
         Employees newEmployee = new Employees(name, lastName, dob, email, departmentDAO.getDepartmentById(deptid));
         employeeDAO.addEmployee(newEmployee);
         response.sendRedirect("list");
     }

     private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ParseException {
         String name = request.getParameter("name");
         String lastName = request.getParameter("lastName");
         Date dob = new SimpleDateFormat("dd/MM/yyyy").parse("dob");
         String email = request.getParameter("email");
         int deptid = Integer.parseInt(request.getParameter("id"));
         Employees newEmployee = new Employees(name, lastName, dob, email, departmentDAO.getDepartmentById(deptid));
         employeeDAO.updateEmployee(newEmployee);
         response.sendRedirect("list");
     }

     private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         employeeDAO.deleteEmployee(id);
         response.sendRedirect("list");
     }
}
