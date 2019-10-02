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
import java.util.List;

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
    public List<BookVO> getAll() {
        List<Book> result = bookMapper.selectByExample(null);
        //调用setAssociate方法设置关联的两个对象
        return setAssociate(result);
    }

    //设置关系对象
    private List<BookVO> setAssociate(List<Book> result) {
        List<BookVO> bookVOList = new ArrayList<>();
        //遍历结果集合，设置每一个书的对象
        for (Book book : result) {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            //查找出对应的种类，再为书设置种类对象
            bookVO.setBookType(bookTypeMapper.selectByPrimaryKey(book.getTypeId()));
            //查找出对应的出版社，再为书设置出版社对象
            bookVO.setPublisher(publisherMapper.selectByPrimaryKey(book.getPubId()));
            bookVOList.add(bookVO);
        }
        return bookVOList;
    }

    @Override
    public BookVO add(BookVO bookVO) {
        bookMapper.insert(bookVO);
        return get(bookVO.getId());
    }

    @Override
    public BookVO update(BookVO book) {
        int id = bookMapper.updateByPrimaryKeySelective(book);
        return get(id);
    }

    @Override
    public List<BookVO> find(String bookName) {
        BookExample bookExample = new BookExample();
        BookExample.Criteria bookExampleCriteria = bookExample.createCriteria();
        bookExampleCriteria.andBookNameLike(bookName);
        List<Book> result = bookMapper.selectByExample(bookExample);
        return setAssociate(result);
    }
}
