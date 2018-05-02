package com.levin.lang.login.service;


import com.levin.lang.login.dto.TbUser;

public interface UserService {
    public TbUser getUserByName(String username);
}
