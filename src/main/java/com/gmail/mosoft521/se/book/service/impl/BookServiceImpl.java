package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.dao.BookMapper;
import com.gmail.mosoft521.se.book.dao.BookTypeMapper;
import com.gmail.mosoft521.se.book.dao.PublisherMapper;
import com.gmail.mosoft521.se.book.entity.Book;
import com.gmail.mosoft521.se.book.entity.BookExample;
import com.gmail.mosoft521.se.book.entity.BookType;
import com.gmail.mosoft521.se.book.entity.Publisher;
import com.gmail.mosoft521.se.book.service.BookService;
import com.gmail.mosoft521.se.book.vo.BookVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 书本业务实现类
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public BookVO get(int id) {
        Book book = bookMapper.selectByPrimaryKey(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        //查找书对应的种类
        BookType bookType = bookTypeMapper.selectByPrimaryKey(book.getTypeId());
        //查找书的出版社
        Publisher publisher = publisherMapper.selectByPrimaryKey(book.getPubId());
        bookVO.setBookType(bookType);
        bookVO.setPublisher(publisher);
        return bookVO;
    }

    @Override
    public Collection<BookVO> getAll() {
        Collection<Book> result = bookMapper.selectByExample(null);
        //调用setAssociate方法设置关联的两个对象
        return setAssociate(result);
    }

    //设置关系对象
    private Collection<BookVO> setAssociate(Collection<Book> result) {
        Collection<BookVO> bookVOs = new ArrayList<BookVO>();
        //遍历结果集合，设置每一个书的对象
        for (Book book : result) {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            //查找出对应的种类，再为书设置种类对象
            bookVO.setBookType(bookTypeMapper.selectByPrimaryKey(book.getTypeId()));
            //查找出对应的出版社，再为书设置出版社对象
            bookVO.setPublisher(publisherMapper.selectByPrimaryKey(book.getPubId()));
            bookVOs.add(bookVO);
        }
        return bookVOs;
    }

    @Override
    public Book add(BookVO book) {
        int id = bookMapper.insert(book);
        return get(id);
    }

    @Override
    public Book update(BookVO book) {
        int id = bookMapper.updateByPrimaryKeySelective(book);
        return get(id);
    }

    @Override
    public Collection<BookVO> find(String name) {
        BookExample bookExample = new BookExample();
        BookExample.Criteria bookExampleCriteria = bookExample.createCriteria();
        bookExampleCriteria.andBookNameLike(name);
        Collection<Book> result = bookMapper.selectByExample(bookExample);
        return setAssociate(result);
    }
}
