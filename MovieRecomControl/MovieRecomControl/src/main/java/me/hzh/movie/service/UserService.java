package me.hzh.movie.service;

import me.hzh.movie.domain.User;

public interface UserService {
    User findByUserName(String userName);

    User save(User user);
}
