import DAO.ComplianceDAO;
import DAO.DepartmentDAO;
import model.Compliance;
import model.Department;

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

@WebServlet(name = "ServletRegulations")
public class ServletRegulations extends HttpServlet {

    private ComplianceDAO complianceDAO;
    private DepartmentDAO departmentDAO;

    public void init() {
        complianceDAO = new ComplianceDAO();
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
                    insertRegulation(request, response);
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
        List<Compliance> listRegulations = complianceDAO.getAllCompliances();
        request.setAttribute("listRegulations", listRegulations);
        System.out.println("ListRegulations "+listRegulations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageRegulations.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Department> deptList = departmentDAO.getAllDepartments();
        RequestDispatcher dispatcher = request.getRequestDispatcher("regulationForm.jsp");
        request.setAttribute("deptList", deptList);
        dispatcher.forward(request, response);
    }

    private void insertRegulation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {
        String type = request.getParameter("type");
        String details = request.getParameter("details");
        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("creationDate").substring(0,10));
        int deptId = Integer.parseInt(request.getParameter("depId"));
        Compliance newCompliance = new Compliance(type, details, creationDate, departmentDAO.getDepartmentById(deptId));
        complianceDAO.addCompliance(newCompliance);
        response.sendRedirect("/ServletRegulations?action=list");
    }


}
