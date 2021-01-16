package com.stackroute.springmvc.entity;




import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

//This is called as Entity class, it is the class that needs to be persists
@Entity
@Table(name = "emp_details")
public class Employee {



    @Id
    @Column(name = "emp_id")
   @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "4"),
                    @Parameter(name = "increment_size", value = "2")
            }
    )

    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    //internally here also hibernate will create a sequence
    // values as 1,2,3,
    private int empId;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_email")
    private String empEmail;
    @Column(name = "emp_contactNo")
    private int empContactNo;

    public Employee() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public int getEmpContactNo() {
        return empContactNo;
    }

    public void setEmpContactNo(int empContactNo) {
        this.empContactNo = empContactNo;
    }

    @Override
    public String
    toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empContactNo=" + empContactNo +
                '}';
    }
}
