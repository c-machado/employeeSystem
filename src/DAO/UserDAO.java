package DAO;

import model.Department;
import model.Employees;
import model.LoginMaster;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    public boolean authenticateUser(int userId, String password) {
        LoginMaster user = getUserByUserId(userId);
        //EmployeeDAO employeeDAO = new EmployeeDAO();
        //Employees id = employeeDAO.getUserById(userId);
        if((user != null) /*&& id !=null*/ && user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public LoginMaster getUserByUserId(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        LoginMaster user = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from model.LoginMaster where empId = :userId");
            query.setParameter("userId",userId);
            user = (LoginMaster) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }


    public List<LoginMaster> getListOfUsers(){
        List<LoginMaster> list = new ArrayList<LoginMaster>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from LoginMaster ").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
