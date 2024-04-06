package com.example.appengine.springboot;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">contextInitialized");
        SessionCookieConfig cc = sce.getServletContext().getSessionCookieConfig();
        cc.setHttpOnly(true);
        cc.setAttribute("SameSite", "Strict");
    }
}
