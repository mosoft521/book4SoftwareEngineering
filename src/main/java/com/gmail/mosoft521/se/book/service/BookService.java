package com.gmail.mosoft521.se.book.service;


import com.gmail.mosoft521.se.book.entity.Book;
import com.gmail.mosoft521.se.book.vo.BookVO;

import java.util.Collection;

/**
 * 书本业务接口
 */
public interface BookService {

    /**
     * 查找全部的书
     *
     * @return
     */
    Collection<BookVO> getAll();

    /**
     * 根据id获取书
     *
     * @param id
     * @return
     */
    BookVO get(int id);

    /**
     * 新增一本书
     *
     * @param book
     * @return
     */
    Book add(BookVO book);

    /**
     * 修改一本书
     *
     * @param book
     * @return
     */
    Book update(BookVO book);

    /**
     * 根据名称模糊查询
     *
     * @param name
     * @return
     */
    Collection<BookVO> find(String name);
}
