package com.levin.lang.login.service.impl;

import com.levin.lang.login.dao.TbUserMapper;
import com.levin.lang.login.dto.TbUser;
import com.levin.lang.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TbUser getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
