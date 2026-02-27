package org.example.service;

import org.example.model.User;

public interface IUserService {
    void register(User user) throws Exception;
    User login(String tagname, String password) throws Exception;
    User getById(int id) throws Exception;
    User getByTagname(String tagname) throws Exception;
}