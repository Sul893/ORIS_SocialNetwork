package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.IUserService;
import org.example.util.HashUtil;
import org.example.util.Validator;

public class UserServiceImpl implements IUserService {
    private UserDAO userDAO;
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) throws Exception {
        if (!Validator.isValidTagname(user.getTagname())) {
            throw new Exception("Некорректный Tagname");
        }
        if (!Validator.isValidPassword(user.getPassword())) {
            throw new Exception("Пароль должен быть от 4 символов");
        }
        if (userDAO.getByTagname(user.getTagname()) != null) {
            throw new Exception("Пользователь уже существует");
        }

        user.setPassword(HashUtil.hash(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User login(String tagname, String password) throws Exception {
        User user = userDAO.getByTagname(tagname);
        if (user == null) {
            throw new Exception("Пользователь не найден");
        }

        String hashedInput = HashUtil.hash(password);
        if (!user.getPassword().equals(hashedInput)) {
            throw new Exception("Неверный пароль");
        }
        return user;
    }

    @Override
    public User getById(int id) throws Exception {
        return userDAO.getById(id);
    }

    @Override
    public User getByTagname(String tagname) throws Exception {
        return userDAO.getByTagname(tagname);
    }

}