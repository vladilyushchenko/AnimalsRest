package com.leverx.animals.dao;

import com.leverx.animals.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> getById(long id);

    User create(User user);

    List<User> getAll();

    User update(User user);

    void deleteById(long id);
}