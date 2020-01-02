package com.lac.wechat.service.impl;

import com.lac.wechat.dao.UserMapper;
import com.lac.wechat.domain.User;
import com.lac.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2020/1/3 0003 - 0:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
