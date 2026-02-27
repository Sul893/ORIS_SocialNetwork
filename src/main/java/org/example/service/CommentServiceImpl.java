package org.example.service;

import org.example.dao.CommentDAO;
import org.example.model.Comment;
import org.example.service.ICommentService;
import org.example.util.Validator;

import java.util.List;

public class CommentServiceImpl implements ICommentService {
    private CommentDAO commentDAO = new CommentDAO();

    @Override
    public void addComment(Comment comment) throws Exception {
        if (!Validator.isValidPostContent(comment.getContent())) {
            throw new Exception("Комментарий пуст или слишком длинный");
        }
        commentDAO.save(comment);
    }

    @Override
    public List<Comment> getComments(int postId) throws Exception {
        return commentDAO.getCommentsByPostId(postId);
    }
}