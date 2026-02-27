package org.example.model;

public class User {
    private Integer id;
    private String tagname;
    private String password;
    private String bio;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    public String getTagname() { return tagname; }
    public void setTagname(String tagname) { this.tagname = tagname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}