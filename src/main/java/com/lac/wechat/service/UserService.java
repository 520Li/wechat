package com.lac.wechat.service;

import com.lac.wechat.domain.User;

/**
 * ClassName: UserService <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2020/1/3 0003 - 0:21
 */
public interface UserService {

    public User getUserById(String id);

    void insertUser(User user);
}
