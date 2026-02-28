package org.example.servlet;

import org.example.model.User;
import org.example.service.IUserService;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    private IUserService userService;

    public RegisterServlet(IUserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tagname = req.getParameter("tagname");
        String password = req.getParameter("password");
        String bio = req.getParameter("bio");

        User user = new User();
        user.setTagname(tagname);
        user.setPassword(password);
        user.setBio(bio);

        try {
            userService.register(user);
            resp.sendRedirect("login");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}