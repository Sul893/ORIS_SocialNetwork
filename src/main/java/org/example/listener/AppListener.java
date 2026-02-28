package org.example.listener;

import org.example.dao.CommentDAO;
import org.example.dao.FollowDAO;
import org.example.dao.PostDAO;
import org.example.model.Comment;
import org.example.service.CommentServiceImpl;
import org.example.service.FollowServiceImpl;
import org.example.service.PostServiceImpl;
import org.example.service.UserServiceImpl;
import org.example.servlet.*;
import org.example.util.DBUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.example.dao.UserDAO;
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

        ServletContext servletContext = sce.getServletContext();


        UserDAO ud = new UserDAO();
        servletContext.setAttribute("UserDAO",ud);
        UserServiceImpl usI  = new UserServiceImpl(ud);
        servletContext.setAttribute("UserServiceImpl",usI);

        FollowDAO fdao = new FollowDAO();
        servletContext.setAttribute("FollowDAO",fdao);
        FollowServiceImpl fsI = new FollowServiceImpl(fdao);
        servletContext.setAttribute("FollowServiceImpl",fsI);

        PostDAO pdao = new PostDAO();
        servletContext.setAttribute("PostDAO", pdao);
        PostServiceImpl psI = new PostServiceImpl(pdao,fdao);
        servletContext.setAttribute("PostServiceImpl",psI);

        CommentDAO cdao = new CommentDAO();
        servletContext.setAttribute("CommnetDAO", cdao);
        CommentServiceImpl csI = new CommentServiceImpl(cdao);
        servletContext.setAttribute("CommentServiceImpl", csI);


        servletContext.addServlet("LoginServlet",new LoginServlet(usI)).addMapping("/login");
        servletContext.addServlet("RegisterServlet", new RegisterServlet(usI)).addMapping("/register");
        servletContext.addServlet("CommentServlet", new CommentServlet(psI,csI)).addMapping("/comments");
        servletContext.addServlet("FollowServlet",new FollowServlet(fsI,usI)).addMapping("/follow");
        servletContext.addServlet("ProfileServlet",new ProfileServlet(usI,psI,fsI)).addMapping("/profile");
        servletContext.addServlet("LikeServlet", new LikeServlet(psI)).addMapping("/like");
        servletContext.addServlet("FeedServlet", new FeedServlet(psI)).addMapping("/feed");
        servletContext.addServlet("LogoutServlet", new LogoutServlet()).addMapping("/logout");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Приложение останавливается...");
    }
}