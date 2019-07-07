package com;

import DAO.ComplianceDAO;
import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import model.Compliance;
import model.Department;
import model.Employees;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main (String args []){

        EmployeeDAO employeeDAO = new EmployeeDAO();
       /* List<Employees> emps = employeeDAO.getAllEmployees();
        for(Employees e: emps){
            System.out.println(e);
        }

        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> depts = departmentDAO.getAllDepartments();
        for(Department d: depts){
            System.out.println(d);
        }
        Department dp = departmentDAO.getDepartmentById(1);*/

        //Compliance compliance = new Compliance("type1", "details1", new Date(),dp);
        //ComplianceDAO complianceDAO = new ComplianceDAO();
        //complianceDAO.deleteCompliance(2);

        Employees emp = employeeDAO.getUserById(2);
        emp.setEmail("carolin@gmail.com");
        employeeDAO.updateEmployee(emp);

        /*StandardServiceRegistry standardRegistry = new
                StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
        SessionFactory factory= metadata.getSessionFactoryBuilder().build();
        //Department p1=new Department("Contabilidad");
        Session session = factory.openSession();
        Query query = session.createQuery("from model.Department where departmentId = :departmentId");
        query.setParameter("departmentId",1);
        Department d1 = (Department) query.getSingleResult();


        Employees p1=new Employees("German","Navarrete", new Date(1986,9,30),"gnavarrete@example.com",d1);


        Transaction transaction = session.beginTransaction();

        session.save(p1);

        transaction.commit();
        session.close();
        //detached
        factory.close();*/
    }
}
