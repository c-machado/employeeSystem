package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Compliance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMPLIANCE_ID",length=10,nullable=false)
    private int complianceId;
    @Column(name="RLTYPE",length=15,nullable=false)
    private String rlType;
    @Column(name="DETAILS",length=250,nullable=false)
    private String details;
    @Column(name="CREATEDATE",nullable=false)
    private Date createDate;

    @ManyToOne
    private Department department;

    public Compliance(){}

    public Compliance(String rlType, String details, Date createDate, Department department) {
        this.rlType = rlType;
        this.details = details;
        this.createDate = createDate;
        this.department = department;
    }

    public int getComplianceId() {
        return complianceId;
    }

    public void setComplianceId(int complianceId) {
        this.complianceId = complianceId;
    }

    public String getRlType() {
        return rlType;
    }

    public void setRlType(String rlType) {
        this.rlType = rlType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
        return "Compliance{" +
                "complianceId=" + complianceId +
                ", rlType='" + rlType + '\'' +
                ", details='" + details + '\'' +
                ", createDate=" + createDate +
                ", department=" + department +
                '}';
    }
}
