package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.commons.BusinessException;
import com.gmail.mosoft521.se.book.dao.UserMapper;
import com.gmail.mosoft521.se.book.entity.UserExample;
import com.gmail.mosoft521.se.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void login(String name, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andUserNameEqualTo(name);
        userExampleCriteria.andUserPasswordEqualTo(password);
        long count = userMapper.countByExample(userExample);
        if (count == 0) {
            throw new BusinessException("用户名密码错误");
        }
    }
}
