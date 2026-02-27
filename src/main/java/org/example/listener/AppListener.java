package org.example.listener;

import org.example.util.DBUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Приложение запускается...");
        try (Connection conn = DBUtil.getConnection()) {
            if (conn != null) {
                System.out.println("бд подкл :)");
            }
        } catch (Exception e) {
            System.out.println("бд НЕ подкл >:(((((");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Приложение останавливается...");
    }
}