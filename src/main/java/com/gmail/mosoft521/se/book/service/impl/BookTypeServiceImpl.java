package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.dao.BookTypeMapper;
import com.gmail.mosoft521.se.book.entity.BookType;
import com.gmail.mosoft521.se.book.entity.BookTypeExample;
import com.gmail.mosoft521.se.book.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * 书本种类业务实现类
 */
@Service("bookTypeService")
@Transactional
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeMapper bookTypeMapper;

    public Collection<BookType> query(String name) {
        BookTypeExample bookTypeExample = new BookTypeExample();
        BookTypeExample.Criteria bookTypeExampleCriteria = bookTypeExample.createCriteria();
        bookTypeExampleCriteria.andTypeNameEqualTo(name);
        return bookTypeMapper.selectByExample(bookTypeExample);
    }

    public Collection<BookType> getAll() {
        return bookTypeMapper.selectByExample(null);
    }

    public BookType add(BookType type) {
        int id = bookTypeMapper.insert(type);
        return get(id);
    }

    public BookType update(BookType type) {
        // TODO Auto-generated method stub
        int id = bookTypeMapper.updateByPrimaryKeySelective(type);
        return get(id);
    }

    public BookType get(int id) {
        return bookTypeMapper.selectByPrimaryKey(id);
    }
}
