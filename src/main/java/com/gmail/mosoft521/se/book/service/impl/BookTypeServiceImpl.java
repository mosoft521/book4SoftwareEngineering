package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.dao.BookTypeMapper;
import com.gmail.mosoft521.se.book.entity.BookType;
import com.gmail.mosoft521.se.book.entity.BookTypeExample;
import com.gmail.mosoft521.se.book.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图书种类业务实现类
 */
@Service("bookTypeService")
@Transactional
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeMapper bookTypeMapper;

    public List<BookType> query(String typeName) {
        BookTypeExample bookTypeExample = new BookTypeExample();
        BookTypeExample.Criteria bookTypeExampleCriteria = bookTypeExample.createCriteria();
        bookTypeExampleCriteria.andTypeNameLike("%" + typeName + "%");
        return bookTypeMapper.selectByExample(bookTypeExample);
    }

    public List<BookType> getAll() {
        return bookTypeMapper.selectByExample(null);
    }

    public BookType add(BookType bookType) {
        bookTypeMapper.insert(bookType);
        return get(bookType.getId());
    }

    public BookType update(BookType bookType) {
        int id = bookTypeMapper.updateByPrimaryKeySelective(bookType);
        return get(id);
    }

    public BookType get(int id) {
        return bookTypeMapper.selectByPrimaryKey(id);
    }
}
