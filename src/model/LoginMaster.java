package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table

public class LoginMaster implements Serializable {
    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "USERID", referencedColumnName = "EMPID")
    private Employees userId;
    @Column(name="PASSWORD",length=30,nullable=false)
    private String password;
    @Column(name="ROLE",length=20,nullable=false)
    private String role;

    public LoginMaster(){}

    public LoginMaster(Employees userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public Employees getUserId() {
        return userId;
    }

    public void setUserId(Employees userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "LoginMaster{" +
                "userId=" + userId.toString() +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
