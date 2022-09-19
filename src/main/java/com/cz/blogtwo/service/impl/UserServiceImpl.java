package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.User;
import com.cz.blogtwo.mapper.UserMapper;
import com.cz.blogtwo.service.UserService;
import com.cz.blogtwo.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!HashUtils.matchBC(password, user.getPassword())) {
            throw new UsernameNotFoundException("密码错误");
        }
        return user;
    }

    @Override
    public User findUserById(Long id) {
        User user = userMapper.findById(id);
        if (user == null) {
            //throw new NotFoundException("用户不存在");
        }
        return user;
    }
}