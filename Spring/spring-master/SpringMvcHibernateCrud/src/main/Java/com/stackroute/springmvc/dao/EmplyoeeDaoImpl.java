package com.stackroute.springmvc.dao;

import com.stackroute.springmvc.entity.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmplyoeeDaoImpl implements IEmployeeDao{

    //Jdbc template
    //Hibernate Template


    private SessionFactory sessionFactory;

   @Autowired
    public EmplyoeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean saveEmployee(Employee employee) {

        try {
            this.sessionFactory.getCurrentSession().save(employee);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Employee getEmployeeByName(String name)
    {
        Query query = null;
        try{
          query  = sessionFactory.getCurrentSession().createQuery("from Employee where empName = :empName");
       query.setParameter("empName" , name);


        }catch (Exception e)
        {
            System.out.println(e);
        }
        return (Employee) query.getResultList().get(0);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {

        return this.sessionFactory.getCurrentSession().createQuery("from Employee").list();

    }

    @Override
    public boolean deleteEmployee(int empId) {
        try {
            //get the data from mysql
            Employee employee = this.sessionFactory.getCurrentSession().load(Employee.class, empId);

            this.sessionFactory.getCurrentSession().delete(employee);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        return false;
    }
}
