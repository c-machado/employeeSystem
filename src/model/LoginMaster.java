package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table

public class LoginMaster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USERID",length=10,nullable=false)
    private int userId;
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "EMPID", referencedColumnName = "EMPID")
    private Employees empId;
    @Column(name="PASSWORD",length=30,nullable=false)
    private String password;
    @Column(name="ROLE",length=20,nullable=false)
    private String role;

    public LoginMaster(){}

    public LoginMaster(Employees empId, String password, String role) {
        this.empId = empId;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Employees getEmpId() {
        return empId;
    }

    public void setEmpId(Employees empId) {
        this.empId = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
