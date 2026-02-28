package org.example.service;

import org.example.dao.FollowDAO;
import org.example.dao.PostDAO;
import org.example.model.Post;
import org.example.service.IPostService;
import org.example.util.Validator;
import java.util.Set;
import java.util.HashSet;


import java.util.List;

public class PostServiceImpl implements IPostService {
    private PostDAO postDAO;
    private FollowDAO followDAO;

    public PostServiceImpl(PostDAO postDAO, FollowDAO followDAO) {
        this.postDAO = postDAO;
        this.followDAO = followDAO;
    }

    @Override
    public void createPost(Post post) throws Exception {
        if (!Validator.isValidPostContent(post.getContent())) {
            throw new Exception("Пост пуст или слишком длинный (макс 280 символов)");
        }
        postDAO.save(post);
    }

    @Override
    public Post getPostById(int id) throws Exception {
        return postDAO.getPostById(id);
    }

    @Override
    public List<Post> getFeed(int currentUserId) throws Exception {
        List<Integer> followingIds = followDAO.getFollowingIds(currentUserId);
        Set<Integer> loadedIds = new HashSet<>();
        List<Post> posts;

        if (followingIds.isEmpty()) {
            posts = postDAO.getAllPosts();
        } else {
            posts = postDAO.getPostsByUserIds(followingIds);

            for (Post p : posts) {
                loadedIds.add(p.getId());
            }
            List<Post> allPosts = postDAO.getAllPosts();
            for (Post p : allPosts) {
                if (!loadedIds.contains(p.getId())) {
                    posts.add(p);
                }
            }
        }

        for (Post p : posts) {
            if (postDAO.isLiked(currentUserId, p.getId())) {
                p.setLiked(true);
            }
        }
        return posts;
    }

    @Override
    public List<Post> getWall(int userId) throws Exception {
        return postDAO.getPostsByUserId(userId);
    }

    @Override
    public void toggleLike(int userId, int postId) throws Exception {
        if (postDAO.isLiked(userId, postId)) {
            postDAO.removeLike(userId, postId);
        } else {
            postDAO.addLike(userId, postId);
        }
    }
}