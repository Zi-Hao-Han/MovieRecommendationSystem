package me.hzh.movie.service.impl;

import me.hzh.movie.domain.User;
import me.hzh.movie.repository.UserRepository;
import me.hzh.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
