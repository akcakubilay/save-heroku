package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;


/**
 * Main Application class
 */
@Configuration
@SpringBootApplication()
public class Application  extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //System.out.println(System.getProperty("user.home")+"\\herokutest");
       //new File(System.getProperty("user.home")+"\\oleeeyy").mkdir();
    }
}
