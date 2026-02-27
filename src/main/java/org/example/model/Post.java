package org.example.model;

import java.sql.Timestamp;

public class Post {
    private Integer id;
    private Integer userId;
    private String content;
    private Timestamp createdAt;
    private Integer likesCount;
    private String authorTagname;
    private boolean liked;

    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Integer getLikesCount() { return likesCount; }
    public void setLikesCount(Integer likesCount) { this.likesCount = likesCount; }
    public String getAuthorTagname() { return authorTagname; }
    public void setAuthorTagname(String authorTagname) { this.authorTagname = authorTagname; }
}