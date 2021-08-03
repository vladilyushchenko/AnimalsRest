package com.leverx.animals.service;

import java.util.List;

public interface UserService {

    User getById(long id);

    User create(User user);

    List<User> getAll();

    User update(User user);

    void deleteById(long id);
}
