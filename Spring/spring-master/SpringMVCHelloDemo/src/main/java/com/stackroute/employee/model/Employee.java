package com.stackroute.employee.model;

public class Employee {

    private int empId;
    private String empName;
    private String empEmail;
    private String empContactNo;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empContactNo='" + empContactNo + '\'' +
                '}';
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

    public String getEmpContactNo() {
        return empContactNo;
    }

    public void setEmpContactNo(String empContactNo) {
        this.empContactNo = empContactNo;
    }

    public Employee(int empId, String empName, String empEmail, String empContactNo) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empContactNo = empContactNo;
    }
}
