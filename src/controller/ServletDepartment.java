import DAO.DepartmentDAO;
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

import java.util.List;

@WebServlet(name = "ServletDepartment")
public class ServletDepartment extends HttpServlet {

    private DepartmentDAO departmentDAO;

    public void init() {
        departmentDAO = new DepartmentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("request " + request.toString());
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertDepartment(request, response);
                    break;
                case "delete":
                    deleteDepartment(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateDepartment(request, response);
                    break;
                default:
                    listDepartment(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Department> listDepartment = departmentDAO.getAllDepartments();
        request.setAttribute("listDepartment", listDepartment);
        System.out.println("ListDepartment "+listDepartment);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDepartment.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("departmentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Department existingDepartment = departmentDAO.getDepartmentById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("departmentForm.jsp");
        request.setAttribute("Department", existingDepartment);
        dispatcher.forward(request, response);

    }

    private void insertDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {
        String name = request.getParameter("name");
        Department department = new Department(name);
        departmentDAO.saveDepartment(department);
        response.sendRedirect("/ServletDepartment?action=list");
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Department department = new Department(id,name);
        departmentDAO.updateDepartment(department);
        response.sendRedirect("/ServletDepartment?action=list");
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentDAO.deleteDepartment(id);
        response.sendRedirect("/ServletDepartment?action=list");
    }
}
