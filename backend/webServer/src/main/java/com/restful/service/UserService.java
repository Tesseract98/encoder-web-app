package com.restful.service;

import com.restful.model.User;

import java.util.List;

public interface UserService {

    void create(User user);

    void update(User user);

    void delete(User user);

    User getById(Long id);

    User getByLogin(String login);

    List<User> getAll();

}
