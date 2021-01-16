package com.stackroute;

import com.stackroute.springdi.bean.Hobby;
import com.stackroute.springdi.bean.Student;
import com.stackroute.springdi.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //java way of creating object
        /*Hobby hobby = new Hobby(1,"chess");
        Student student = new Student(1,2,3,hobby);
        List object will be created
        Hobby object will be created
        hobby will be added inside the list;
        list will be given to student
        System.out.println(hobby);
        System.out.println( "Hello World!" );*/


        //Spring way of creating object
//BeanFactory - getBean() -
      //  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

       Hobby hobby= applicationContext.getBean("hobby1",Hobby.class);
        System.out.println(hobby);

        /*Hobby hobby1= applicationContext.getBean("hobby2",Hobby.class);
        System.out.println(hobby1);*/

       /* Hobby hobby1= applicationContext.getBean("hobby1",Hobby.class);
        System.out.println(hobby1.hashCode());*/

      /*  Hobby hobby1= applicationContext.getBean("hobby2",Hobby.class);
        System.out.println(hobby1.hashCode());*/

        /*Student student= applicationContext.getBean("student1",Student.class);
        System.out.println(student);*/

        Student student2= applicationContext.getBean(Student.class);
        System.out.println(student2);

       /* student.getHobbyList().forEach(hobby -> System.out.println(hobby.getHobbyName()));*/

    }
}
