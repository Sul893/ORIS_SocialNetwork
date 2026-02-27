package org.example.dao;

import org.example.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowDAO {


    public void follow(int followerId, int followingId) throws SQLException {
        String sql = "INSERT INTO followers (follower_id, following_id) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, followerId);
            ps.setInt(2, followingId);
            ps.executeUpdate();
        }
    }

    public void unfollow(int followerId, int followingId) throws SQLException {
        String sql = "DELETE FROM followers WHERE follower_id = ? AND following_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, followerId);
            ps.setInt(2, followingId);
            ps.executeUpdate();
        }
    }


    public boolean isFollowing(int followerId, int followingId) throws SQLException {
        String sql = "SELECT 1 FROM followers WHERE follower_id = ? AND following_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, followerId);
            ps.setInt(2, followingId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    public List<Integer> getFollowingIds(int userId) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT following_id FROM followers WHERE follower_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt("following_id"));
                }
            }
        }
        return ids;
    }
}