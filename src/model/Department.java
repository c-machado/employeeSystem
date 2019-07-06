package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPARTMENT_ID",length=10,nullable=false)
    private int departmentId;
    @Column(name="DEPARTMENT_NM",length=25,nullable=false)
    private String departmentName;


    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(){}

    public int getDepartmentid() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
