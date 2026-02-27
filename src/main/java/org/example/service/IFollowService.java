package org.example.service;

public interface IFollowService {
    void follow(int followerId, int followingId) throws Exception;
    void unfollow(int followerId, int followingId) throws Exception;
    boolean isFollowing(int followerId, int followingId) throws Exception;
}