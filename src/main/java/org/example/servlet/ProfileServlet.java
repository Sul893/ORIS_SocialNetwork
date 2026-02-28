package org.example.servlet;

import org.example.model.Post;
import org.example.model.User;
import org.example.service.IFollowService;
import org.example.service.IPostService;
import org.example.service.IUserService;
import org.example.service.FollowServiceImpl;
import org.example.service.PostServiceImpl;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class ProfileServlet extends HttpServlet {
    private IUserService userService;
    private IPostService postService;
    private IFollowService followService;

    public ProfileServlet(IUserService userService, IPostService postService, IFollowService followService) {
        this.userService = userService;
        this.postService = postService;
        this.followService = followService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect("login");
            return;
        }

        String tagname = req.getParameter("u");
        User profileUser;

        try {
            if (tagname != null && !tagname.isEmpty()) {
                profileUser = userService.getByTagname(tagname);

                if (profileUser == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Пользователь не найден");
                    return;
                }
            } else {
                profileUser = currentUser;
            }

            List<Post> posts = postService.getWall(profileUser.getId());


            boolean isFollowing = false;
            if (!currentUser.getId().equals(profileUser.getId())) {
                isFollowing = followService.isFollowing(currentUser.getId(), profileUser.getId());
            }


            req.setAttribute("profileUser", profileUser);
            req.setAttribute("posts", posts);
            req.setAttribute("isFollowing", isFollowing);

            req.setAttribute("currentUser", currentUser);

            req.getRequestDispatcher("profile.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Ошибка при загрузке профиля", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        String content = req.getParameter("content");

        Post post = new Post();
        post.setUserId(user.getId());
        post.setContent(content);

        try {
            postService.createPost(post);
            resp.sendRedirect("profile");
        } catch (Exception e) {
            resp.sendRedirect("profile");
        }
    }
}