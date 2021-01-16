package com.stackroute.springdi.config;


import com.stackroute.springdi.bean.Hobby;
import com.stackroute.springdi.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@ImportResource("classpath:beans.xml")
@ComponentScan(basePackages = "com.stackroute.springdi.bean")
//This is a configuration class, here we declare factory methods.

public class AppConfig {

        @Bean(name = "hobby4")
          public Hobby getHobby(){
        return new Hobby(101,"Playing chess");
   }

   @Bean(name = "hobby5")
    public Hobby getHobby1(){
        return new Hobby(102,"Playing tenis");
    }

    @Bean(name = "hobby6")
    public Hobby getHobby2(){
        return new Hobby(103,"Playing football");
    }

  /*  @Bean(name = "student1")
    public Student getStudent1()
    {
        return new Student(1001,"Student1","student1@gmail.com",new ArrayList<Hobby>(Arrays.asList(getHobby1(),getHobby(),getHobby())));
    }*/
  /*  @Bean(name = "student2")
    public Student getStudent2()
    {
        return new Student(1002,"Student2","student2@gmail.com",getHobby1());
    }*/
}
