package org.example.servlet;

import org.example.model.Post;
import org.example.model.User;
import org.example.service.IPostService;
import org.example.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class FeedServlet extends HttpServlet {
    private IPostService postService;

    public FeedServlet(IPostService postService) {
        this.postService = postService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }
        User currentUser = (User) session.getAttribute("user");

        try {
            List<Post> posts = postService.getFeed(currentUser.getId());
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("feed.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}