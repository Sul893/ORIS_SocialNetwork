package org.example.servlet;

import org.example.model.User;
import org.example.service.IUserService;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tagname = req.getParameter("tagname");
        String password = req.getParameter("password");

        try {
            User user = userService.login(tagname, password);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("feed");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}