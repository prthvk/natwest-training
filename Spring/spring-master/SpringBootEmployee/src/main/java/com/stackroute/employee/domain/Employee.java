package com.stackroute.employee.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {


    @Id
    private int empid;
    private String empname;
    private String empemail;

    @Override
    public String toString() {
        return "Employee{" +
                "empid=" + empid +
                ", empname='" + empname + '\'' +
                ", empemail='" + empemail + '\'' +
                '}';
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    public Employee() {
    }

    public Employee(int empid, String empname, String empemail) {
        this.empid = empid;
        this.empname = empname;
        this.empemail = empemail;
    }
}
