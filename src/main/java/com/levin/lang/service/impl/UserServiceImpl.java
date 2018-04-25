package com.levin.lang.service.impl;

import com.levin.lang.dao.TbUserMapper;
import com.levin.lang.dto.TbUser;
import com.levin.lang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TbUser getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
