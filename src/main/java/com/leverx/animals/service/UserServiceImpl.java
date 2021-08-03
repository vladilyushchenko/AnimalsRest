package com.leverx.animals.service;

import com.leverx.animals.exception.NotFoundException;
import com.leverx.animals.repository.UserRepository;
import com.leverx.animals.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id).orElseThrow(() -> {
            throw new NotFoundException(User.class, id);
        });
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
