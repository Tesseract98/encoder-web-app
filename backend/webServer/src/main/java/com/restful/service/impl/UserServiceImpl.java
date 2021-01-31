package com.restful.service.impl;

import com.restful.exceptions.service.NoSuchUserException;
import com.restful.model.User;
import com.restful.repository.UserRepo;
import com.restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void create(User user) {
        userRepo.save(user);
    }

    @Override
    public void update(User user) {
        this.getById(user.getId());
        userRepo.save(user);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(NoSuchUserException::new);
    }

    @Override
    public User getByLogin(String login) {
        return userRepo.findByLogin(login).orElseThrow(NoSuchUserException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

}
