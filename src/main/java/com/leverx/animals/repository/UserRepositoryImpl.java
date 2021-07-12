package com.leverx.animals.repository;

import com.leverx.animals.dao.UserDao;
import com.leverx.animals.dao.UserDaoImpl;
import com.leverx.animals.entity.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public Optional<User> getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
