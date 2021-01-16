package com.stackroute.springmvc.config;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.springmvc.entity.Employee;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


//configuration files about Hibernate
@Configuration
@ComponentScan("com.stackroute.springmvc")
//This annotation is used for enabling spring MVC is an application and works for importing Spring MVC configuration
@EnableWebMvc
@EnableTransactionManagement
public class AppConfig {

    //bean defination


    //dataSource - Configuration of database properties , helps to connect to db.
    /*
    Datasource is a name given to the connection set up to a database from a server.
    The name is commonly used when creating a query to the database.
    The data source name (DSN) need not be the same as the filename for the database.
     */

    @Bean
    public ViewResolver getViewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
       // dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //dataSource.setUrl("jdbc:mysql://localhost:3306/testempdb?useSSL=false");
        dataSource.setUrl("jdbc:mysql://localhost:3306/newmyempdb?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;

    }

    //SessionFactory
    /*
      SessionFactory is an interface.
      It will contain all the db related properties
      SessionFactory is a factory for Session objects.
      We can create one SessionFactory implementation per database in any application
      If your application is referring to multiple databases,
      then you need to create one SessionFactory per database.

      here spring provides abstraction of ur sessionfactory by giving LocalSessionfactory

     */

    @Bean
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) throws IOException {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        /*
        Dialect means "the variant of a language". Hibernate, as we know, is database agnostic.
        It can work with different databases.
        However, databases have proprietary extensions/native SQL variations, and set/sub-set of
        SQL standard implementations.
        Therefore at some point hibernate has to use database specific SQL.
        Hibernate uses "dialect" configuration to know which database
        you are using so that it can switch to the database specific
         SQL generator code wherever/whenever necessary.
         */
        properties.put("hibernate.hbm2ddl.auto", "update");
        /*
         The hbm2ddl.auto property of Hibernate either creates or validates a database table.
         The possible values for hbm2ddl.auto:
         1)Create - If the value is CREATE then the hibernate first drops the existing tables data and structure,
          then creates new tables and executes the operations on the newly created tables.
          2)If the value is validate then hibernate only validates the table structure- whether the
          table and columns have existed or not. If the table doesn’t exist then hibernate throws an exception.
           Validate is the default value for hbm2ddl.auto.
         3)If the value is update then, Hibernate checks for the table and columns.
           If a table doesn’t exist then it creates new tables and where as if a column doesn’t exist
           it creates new columns for it.
           4)If the value is create-drop then, Hibernate first checks for a table and do the necessary operations
           and finally drops the table after all the operations were completed.
           The value “create-drop” is given for unit testing the hibernate code.
           5)
         */

        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setAnnotatedClasses(Employee.class);
        //sessionFactoryBean.afterPropertiesSet();


        return sessionFactoryBean;


    }

    //all transactions will be handle by this class and this class needs an object of SessionFactory.
    /*

     */

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }
}



