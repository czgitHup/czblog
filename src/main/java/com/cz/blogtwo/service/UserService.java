package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.User;

public interface UserService {
    User findUserByUsernameAndPassword(String username, String password);

    User findUserById(Long id);
}
