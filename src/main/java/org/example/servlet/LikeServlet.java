package org.example.servlet;

import org.example.model.User;
import org.example.service.IPostService;
import org.example.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    private IPostService postService = new PostServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        int postId = Integer.parseInt(req.getParameter("postId"));

        try {
            postService.toggleLike(user.getId(), postId);

            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}