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
import java.util.Locale;

@WebServlet(name = "/ServletEmployees"/*,urlPatterns = {"/Employees"}*/ )
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
         String action = request.getParameter("action");
         try {
             switch (action) {
                 case "new":
                     showNewForm(request, response);
                     break;
                 case "insert":
                     insertEmployee(request, response);
                     break;
                 case "delete":
                     deleteEmployee(request, response);
                     break;
                 case "edit":
                     showEditForm(request, response);
                     break;
                 case "update":
                     updateEmployee(request, response);
                     break;
                 default:
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


         RequestDispatcher dispatcher = request.getRequestDispatcher("manageEmployees.jsp");
         dispatcher.forward(request, response);
     }

     private void showNewForm(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("employeeForm.jsp");
         dispatcher.forward(request, response);
     }

     private void showEditForm(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         List<Department> deptList = departmentDAO.getAllDepartments();
         Employees existingEmployee = employeeDAO.getUserById(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("employeeForm.jsp");
         request.setAttribute("Employee", existingEmployee);
         request.setAttribute("deptList", deptList);
         dispatcher.forward(request, response);

     }

     private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ParseException {
         String name = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob").substring(0,10));
                 //SimpleDateFormat("dd/MM/yyyy").parse("dob");
         String email = request.getParameter("email");
         int deptId = Integer.parseInt(request.getParameter("depId"));
         Employees newEmployee = new Employees(name, lastName, dob, email, departmentDAO.getDepartmentById(deptId));
         employeeDAO.addEmployee(newEmployee);
         response.sendRedirect("/ServletEmployees?action=list");
     }

     private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ParseException {
         int id = Integer.parseInt(request.getParameter("id"));
         String name = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob").substring(0,10));
         String email = request.getParameter("email");
         int depid = Integer.parseInt(request.getParameter("depId"));
         Employees newEmployee = employeeDAO.getUserById(id);
         newEmployee.setFirstName(name);
         newEmployee.setLastName(lastName);
         newEmployee.setDob(dob);
         newEmployee.setEmail(email);
         newEmployee.setDepartment(departmentDAO.getDepartmentById(depid));
         //Employees newEmployee = new Employees(name, lastName, dob, email, departmentDAO.getDepartmentById(depid));
         employeeDAO.updateEmployee(newEmployee);
         response.sendRedirect("/ServletEmployees?action=list");
     }

     private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         employeeDAO.deleteEmployee(id);
         response.sendRedirect("/ServletEmployees?action=list");
     }
}
