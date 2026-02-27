package org.example.servlet;

import org.example.model.User;
import org.example.service.IFollowService;
import org.example.service.IUserService;
import org.example.service.FollowServiceImpl;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
    private IFollowService followService = new FollowServiceImpl();
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        String targetTag = req.getParameter("target");
        String action = req.getParameter("action");

        try {
            User targetUser = userService.getByTagname(targetTag);

            if (targetUser != null && !targetUser.getId().equals(currentUser.getId())) {
                if ("follow".equals(action)) {
                    followService.follow(currentUser.getId(), targetUser.getId());
                } else if ("unfollow".equals(action)) {
                    followService.unfollow(currentUser.getId(), targetUser.getId());
                }
            }
            resp.sendRedirect("profile?u=" + targetTag);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}