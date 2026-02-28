package org.example.service;

import org.example.dao.FollowDAO;
import org.example.service.IFollowService;

public class FollowServiceImpl implements IFollowService {
    private FollowDAO followDAO;

    public FollowServiceImpl(FollowDAO followDAO){
        this.followDAO=followDAO;
    }

    @Override
    public void follow(int followerId, int followingId) throws Exception {
        followDAO.follow(followerId, followingId);
    }

    @Override
    public void unfollow(int followerId, int followingId) throws Exception {
        followDAO.unfollow(followerId, followingId);
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) throws Exception {
        return followDAO.isFollowing(followerId, followingId);
    }
}