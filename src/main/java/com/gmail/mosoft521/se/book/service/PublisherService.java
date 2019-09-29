package com.gmail.mosoft521.se.book.service;

import com.gmail.mosoft521.se.book.entity.Publisher;

import java.util.Collection;

/**
 * 出版社业务接口
 */
public interface PublisherService {

    /**
     * 获取全部的出版社
     *
     * @return
     */
    Collection<Publisher> getAll();

    /**
     * 根据id查找一个出版社
     *
     * @param id 出版社id
     * @return
     */
    Publisher find(int id);

    /**
     * 添加一个出版社
     *
     * @param c
     * @return
     */
    Publisher add(Publisher c);

    /**
     * 修改一个出版社
     *
     * @param c
     * @return
     */
    Publisher update(Publisher c);

    /**
     * 根据出版社名字模糊查找
     *
     * @param name
     * @return
     */
    Collection<Publisher> query(String name);
}
