package org.example.service;

import org.example.model.Post;
import java.util.List;

public interface IPostService {
    void createPost(Post post) throws Exception;
    Post getPostById(int id) throws Exception;
    List<Post> getFeed(int currentUserId) throws Exception;
    List<Post> getWall(int userId) throws Exception;
    void toggleLike(int userId, int postId) throws Exception;
}