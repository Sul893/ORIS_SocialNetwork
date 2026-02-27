package org.example.service;

import org.example.model.Comment;
import java.util.List;

public interface ICommentService {
    void addComment(Comment comment) throws Exception;
    List<Comment> getComments(int postId) throws Exception;
}