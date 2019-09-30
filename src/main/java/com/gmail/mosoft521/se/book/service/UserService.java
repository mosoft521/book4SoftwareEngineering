package com.gmail.mosoft521.se.book.service;

/**
 * 用户业务接口
 */
public interface UserService {
    /**
     * 用户登录的方法, 如果登录失败，则抛出BusinessException
     *
     * @param userName 用户名
     * @param userPassword 密码
     */
    void login(String userName, String userPassword);
}
