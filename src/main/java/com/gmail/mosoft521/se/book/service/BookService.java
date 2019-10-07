package com.gmail.mosoft521.se.book.service;

import com.gmail.mosoft521.se.book.vo.BookVO;

import java.util.List;

/**
 * 图书业务接口
 */
public interface BookService {

    /**
     * 查找全部的书
     *
     * @return
     */
    List<BookVO> getAll();

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
     * @param bookVO
     * @return
     */
    BookVO add(BookVO bookVO);

    /**
     * 修改一本书
     *
     * @param bookVO
     * @return
     */
    BookVO update(BookVO bookVO);

    /**
     * 根据名称模糊查询
     *
     * @param name
     * @return
     */
    List<BookVO> find(String bookName);
}
