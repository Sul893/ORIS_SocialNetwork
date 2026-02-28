package org.example.servlet;

import org.example.model.Comment;
import org.example.model.Post;
import org.example.model.User;
import org.example.service.ICommentService;
import org.example.service.IPostService;
import org.example.service.CommentServiceImpl;
import org.example.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class CommentServlet extends HttpServlet {
    private ICommentService commentService;
    private IPostService postService;

    public CommentServlet(IPostService postService, ICommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("postId"));
        try {
            Post post = postService.getPostById(postId);
            List<Comment> comments = commentService.getComments(postId);

            req.setAttribute("post", post);
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("comments.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        int postId = Integer.parseInt(req.getParameter("postId"));
        String content = req.getParameter("content");
        String parentIdStr = req.getParameter("parentId");

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(user.getId());
        comment.setContent(content);

        if (parentIdStr != null && !parentIdStr.isEmpty()) {
            comment.setParentId(Integer.parseInt(parentIdStr));
        }

        try {
            commentService.addComment(comment);
            resp.sendRedirect("comments?postId=" + postId);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}