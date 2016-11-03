package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class SimpleServlet extends HttpServlet{

    UserService userService;

    @Override
    public void init() throws ServletException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        userService = (UserService)context.getBean("userService");

    }



}
