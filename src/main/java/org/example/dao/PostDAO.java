package org.example.dao;

import org.example.util.DBUtil;
import org.example.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    public void save(Post post) throws SQLException {
        String sql = "INSERT INTO posts (user_id, content) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, post.getUserId());
            ps.setString(2, post.getContent());
            ps.executeUpdate();
        }
    }

    public List<Post> getPostsByUserId(int userId) throws SQLException {
        List<Post> posts = new ArrayList<>();

        String sql = "SELECT p.*, u.tagname FROM posts p JOIN users u ON p.user_id = u.id WHERE p.user_id = ? ORDER BY p.created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posts.add(mapRow(rs));
                }
            }
        }
        return posts;
    }


    private Post mapRow(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setUserId(rs.getInt("user_id"));
        post.setContent(rs.getString("content"));
        post.setCreatedAt(rs.getTimestamp("created_at"));
        post.setLikesCount(rs.getInt("likes_count"));
        post.setAuthorTagname(rs.getString("tagname"));
        return post;
    }

    public List<Post> getAllPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.*, u.tagname FROM posts p JOIN users u ON p.user_id = u.id ORDER BY p.likes_count DESC, p.created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                posts.add(mapRow(rs));
            }
        }
        return posts;
    }
    public Post getPostById(int id) throws SQLException {
        String sql = "SELECT p.*, u.tagname FROM posts p JOIN users u ON p.user_id = u.id WHERE p.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }
    public List<Post> getPostsByUserIds(List<Integer> userIds) throws SQLException {
        List<Post> posts = new ArrayList<>();
        if (userIds == null || userIds.isEmpty()) {
            return posts;
        }


        StringBuilder sb = new StringBuilder("SELECT p.*, u.tagname FROM posts p JOIN users u ON p.user_id = u.id WHERE p.user_id IN (");
        for (int i = 0; i < userIds.size(); i++) {
            sb.append("?");
            if (i < userIds.size() - 1){sb.append(",");}
        }
        sb.append(") ORDER BY p.created_at DESC");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            for (int i = 0; i < userIds.size(); i++) {
                ps.setInt(i + 1, userIds.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posts.add(mapRow(rs));
                }
            }
        }
        return posts;
    }


    public void addLike(int userId, int postId) throws SQLException {

        String sqlLike = "INSERT INTO likes (user_id, post_id) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlLike)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            ps.executeUpdate();
        }

        updateLikesCount(postId, 1);
    }

    public void removeLike(int userId, int postId) throws SQLException {
        String sqlLike = "DELETE FROM likes WHERE user_id = ? AND post_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlLike)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                updateLikesCount(postId, -1);
            }
        }
    }


    private void updateLikesCount(int postId, int delta) throws SQLException {
        String sql = "UPDATE posts SET likes_count = likes_count + ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, delta);
            ps.setInt(2, postId);
            ps.executeUpdate();
        }
    }


    public boolean isLiked(int userId, int postId) throws SQLException {
        String sql = "SELECT 1 FROM likes WHERE user_id = ? AND post_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


}