package com.gmail.mosoft521.se.book.service;

import com.gmail.mosoft521.se.book.entity.BookType;

import java.util.Collection;

/**
 * 书本种类业务接口
 */
public interface BookTypeService {

    /**
     * 查找所有的种类
     *
     * @return 返回种类值对象集合
     */
    Collection<BookType> getAll();

    /**
     * 根据种类名字模糊查找种类
     *
     * @param name 种类名称
     * @return 查找的结果集
     */
    Collection<BookType> query(String name);

    /**
     * 新增一个书本种类
     *
     * @param type 需要新增的对象
     * @return 新增后的种类对象
     */
    BookType add(BookType type);

    /**
     * 修改一个书本种类
     *
     * @param type 需要修改的对象
     * @return 修改后的对象
     */
    BookType update(BookType type);

    /**
     * 根据主键查找一个种类
     *
     * @param id
     * @return
     */
    BookType get(int id);
}
