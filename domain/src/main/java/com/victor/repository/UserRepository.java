package com.victor.repository;

import com.victor.entity.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);
}
