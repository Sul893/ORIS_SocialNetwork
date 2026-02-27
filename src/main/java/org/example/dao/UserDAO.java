package org.example.dao;

import org.example.util.DBUtil;
import org.example.model.User;

import java.sql.*;

public class UserDAO {
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users (tagname, password, bio) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getTagname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getBio());
            ps.executeUpdate();
        }
    }

    public User getByTagname(String tagname) throws SQLException {
        String sql = "SELECT * FROM users WHERE tagname = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tagname);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setTagname(rs.getString("tagname"));
                    user.setPassword(rs.getString("password"));
                    user.setBio(rs.getString("bio"));
                    return user;
                }
            }
        }
        return null;
    }
    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setTagname(rs.getString("tagname"));
                    user.setPassword(rs.getString("password"));
                    user.setBio(rs.getString("bio"));
                    return user;
                }
            }
        }
        return null;
    }
}