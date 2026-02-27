package org.example.dao;

import org.example.util.DBUtil;
import org.example.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public void save(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (post_id, user_id, parent_id, content) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comment.getPostId());
            ps.setInt(2, comment.getUserId());

            if (comment.getParentId() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, comment.getParentId());
            }
            ps.setString(4, comment.getContent());
            ps.executeUpdate();
        }
    }

    public List<Comment> getCommentsByPostId(int postId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.*, u.tagname, pu.tagname as parent_tagname " +
                "FROM comments c " +
                "JOIN users u ON c.user_id = u.id " +
                "LEFT JOIN comments pc ON c.parent_id = pc.id " +
                "LEFT JOIN users pu ON pc.user_id = pu.id " +
                "WHERE c.post_id = ? ORDER BY c.created_at";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, postId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comment c = new Comment();
                    c.setId(rs.getInt("id"));
                    c.setPostId(rs.getInt("post_id"));
                    c.setUserId(rs.getInt("user_id"));
                    c.setParentId(rs.getInt("parent_id"));
                    if (rs.wasNull()) c.setParentId(null);
                    c.setContent(rs.getString("content"));
                    c.setCreatedAt(rs.getTimestamp("created_at"));
                    c.setAuthorTagname(rs.getString("tagname"));
                    c.setParentAuthorTagname(rs.getString("parent_tagname"));
                    comments.add(c);
                }
            }
        }
        return comments;
    }

}