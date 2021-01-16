package com.stackroute.employee.domain;


import com.stackroute.employee.repository.AddessRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {


    @Id
    private int empid;
    private String empname;
    private String empemail;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

    @OneToMany(cascade = CascadeType.ALL)
   // @JoinColumn(name = "empid")
    private List<Address> addressList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

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
