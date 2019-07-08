package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class StatusReport implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "COMPLIANCE_ID", referencedColumnName = "COMPLIANCE_ID")
    private Compliance compliance;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STATUSRPTID",length=10,nullable=false)
    private int statusRptId;
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "EMPID", referencedColumnName = "EMPID")
    private Employees empId;
    @Column(name="COMMENTS",length=15,nullable=false)
    private String comments;
    @Column(name="CREATEDATE",nullable=false)
    private Date createDate;
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private Department department;

    public StatusReport(){}

    public StatusReport(Compliance compliance, /*int statusRptId,*/ Employees empId, String comments, Date createDate, Department department) {
        this.compliance = compliance;
        //this.statusRptId = statusRptId;
        this.empId = empId;
        this.comments = comments;
        this.createDate = createDate;
        this.department = department;
    }

    public Compliance getCompliance() {
        return compliance;
    }

    public void setCompliance(Compliance compliance) {
        this.compliance = compliance;
    }

    public int getStatusRptId() {
        return statusRptId;
    }

    public void setStatusRptId(int statusRptId) {
        this.statusRptId = statusRptId;
    }

    public Employees getEmpId() {
        return empId;
    }

    public void setEmpId(Employees empId) {
        this.empId = empId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "StatusReport{" +
                "compliance=" + compliance +
                ", statusRptId=" + statusRptId +
                ", empId=" + empId +
                ", comments='" + comments + '\'' +
                ", createDate=" + createDate +
                ", department=" + department +
                '}';
    }
}
