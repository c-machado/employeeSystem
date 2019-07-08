import DAO.ComplianceDAO;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import DAO.StatusReportDAO;
import model.Compliance;
import model.Department;
import model.Employees;
import model.StatusReport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ServletStatusReport"/*,urlPatterns = {"/Employees"}*/ )
public class ServletStatusReport extends HttpServlet {

    //private static final long serialVersionUID = 1 L;

    private StatusReportDAO statusReportDAO;
    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;
    private ComplianceDAO complianceDAO;

    public void init() {
        statusReportDAO = new StatusReportDAO();
        departmentDAO = new DepartmentDAO();
        employeeDAO = new EmployeeDAO();
        complianceDAO = new ComplianceDAO();
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
                     assignEmployee(request, response);
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
                     listRegulations(request, response);
                     break;
             }
         } catch (SQLException | ParseException ex) {
             throw new ServletException(ex);
         }
     }

    private void listRegulations(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<StatusReport> listRegulations = statusReportDAO.getAllStatusReports();
        request.setAttribute("listRegulations", listRegulations);

        System.out.println("ListRegulations "+listRegulations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageStatusRegulations.jsp");
        dispatcher.forward(request, response);
    }

     private void showNewForm(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         List<Department> deptList = departmentDAO.getAllDepartments();
         List<Compliance> compList = complianceDAO.getAllCompliances();
         List<Employees> empList = employeeDAO.getAllEmployees();

         RequestDispatcher dispatcher = request.getRequestDispatcher("statusForm.jsp");
         request.setAttribute("deptList", deptList);
         request.setAttribute("compList",compList);
         request.setAttribute("userList",empList);

         dispatcher.forward(request, response);
     }

     private void showEditForm(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         List<Department> deptList = departmentDAO.getAllDepartments();
         Employees existingEmployee = employeeDAO.getUserById(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("statusForm.jsp");
         request.setAttribute("Employee", existingEmployee);
         request.setAttribute("deptList", deptList);
         dispatcher.forward(request, response);

     }

     private void assignEmployee(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ParseException {
         int compId = Integer.parseInt(request.getParameter("compId"));
         int userId = Integer.parseInt(request.getParameter("userId"));
         String comments = request.getParameter("comments");
         Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createDate").substring(0,10));
         int deptId = Integer.parseInt(request.getParameter("depId"));
         StatusReport newStatus = new StatusReport(complianceDAO.getComplianceById(compId), employeeDAO.getUserById(userId), comments,
                  createDate, departmentDAO.getDepartmentById(deptId));
         statusReportDAO.addStatusReport(newStatus);
         response.sendRedirect("/ServletStatusReport?action=list");
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
