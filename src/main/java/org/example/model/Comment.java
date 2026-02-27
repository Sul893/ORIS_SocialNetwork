package org.example.model;

import java.sql.Timestamp;

public class Comment {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private Integer parentId;
    private String content;
    private Timestamp createdAt;
    private String authorTagname;

    private String parentAuthorTagname;

    public String getParentAuthorTagname() { return parentAuthorTagname; }
    public void setParentAuthorTagname(String parentAuthorTagname) { this.parentAuthorTagname = parentAuthorTagname; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPostId() { return postId; }
    public void setPostId(Integer postId) { this.postId = postId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public String getAuthorTagname() { return authorTagname; }
    public void setAuthorTagname(String authorTagname) { this.authorTagname = authorTagname; }
}