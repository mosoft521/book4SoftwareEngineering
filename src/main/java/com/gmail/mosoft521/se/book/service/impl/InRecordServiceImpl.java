package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.commons.DateUtil;
import com.gmail.mosoft521.se.book.dao.BookInRecordMapper;
import com.gmail.mosoft521.se.book.dao.BookMapper;
import com.gmail.mosoft521.se.book.dao.BookTypeMapper;
import com.gmail.mosoft521.se.book.dao.InRecordMapper;
import com.gmail.mosoft521.se.book.dao.PublisherMapper;
import com.gmail.mosoft521.se.book.entity.Book;
import com.gmail.mosoft521.se.book.entity.BookInRecord;
import com.gmail.mosoft521.se.book.entity.BookInRecordExample;
import com.gmail.mosoft521.se.book.entity.BookType;
import com.gmail.mosoft521.se.book.entity.InRecord;
import com.gmail.mosoft521.se.book.entity.InRecordExample;
import com.gmail.mosoft521.se.book.entity.Publisher;
import com.gmail.mosoft521.se.book.service.InRecordService;
import com.gmail.mosoft521.se.book.vo.BookInRecordVO;
import com.gmail.mosoft521.se.book.vo.BookVO;
import com.gmail.mosoft521.se.book.vo.InRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 入库记录业务实现类
 */
@Service("inRecordService")
@Transactional
public class InRecordServiceImpl implements InRecordService {
    @Autowired
    private InRecordMapper inRecordMapper;

    @Autowired
    private BookInRecordMapper bookInRecordMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public List<InRecordVO> getAll(Date date) {
        //得到下一天
        Date nextDate = DateUtil.getNextDate(date);
        InRecordExample inRecordExample = new InRecordExample();
        InRecordExample.Criteria inRecordExampleCriteria = inRecordExample.createCriteria();
        inRecordExampleCriteria.andRecordDateGreaterThanOrEqualTo(date);
        inRecordExampleCriteria.andRecordDateLessThanOrEqualTo(nextDate);
        List<InRecord> inRecordList = inRecordMapper.selectByExample(inRecordExample);
        List<InRecordVO> inRecordVOList = new ArrayList<>();
        for (InRecord inRecord : inRecordList) {
            InRecordVO inRecordVO = new InRecordVO();
            BeanUtils.copyProperties(inRecord, inRecordVO);
            processData(inRecordVO);
            inRecordVOList.add(inRecordVO);
        }
        return inRecordVOList;
    }

    private InRecordVO processData(InRecordVO inRecordVO) {
        BookInRecordExample bookInRecordExample = new BookInRecordExample();
        BookInRecordExample.Criteria bookInRecordExampleCriteria = bookInRecordExample.createCriteria();
        bookInRecordExampleCriteria.andInRecordIdEqualTo(inRecordVO.getId());
        List<BookInRecord> bookInRecordList = bookInRecordMapper.selectByExample(bookInRecordExample);
        List<BookInRecordVO> bookInRecordVOList = new ArrayList<>();
        //设置记录中的每一本书
        setBook(bookInRecordList, bookInRecordVOList);
        //设置入库记录中的书的入库记录
        inRecordVO.setBookInRecordVOs(bookInRecordVOList);
        //设置书名
        inRecordVO.setBookNames(getBookNames(bookInRecordVOList));
        //设置书总数
        inRecordVO.setAmount(getAmount(bookInRecordVOList));
        return inRecordVO;
    }

    //获取一次入库记录中所有书本的交易量
    private int getAmount(List<BookInRecordVO> bookInRecordVOList) {
        int result = 0;
        for (BookInRecord bookInRecord : bookInRecordVOList) {
            result += bookInRecord.getInSum();
        }
        return result;
    }

    //设置参数中的每一个BookInRecord的book属性
    private void setBook(List<BookInRecord> bookInRecords, List<BookInRecordVO> bookInRecordVOList) {
        for (BookInRecord bookInRecord : bookInRecords) {
            Book book = bookMapper.selectByPrimaryKey(bookInRecord.getBookId());
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            //查找书对应的种类
            BookType bookType = bookTypeMapper.selectByPrimaryKey(book.getTypeId());
            //查找书的出版社
            Publisher publisher = publisherMapper.selectByPrimaryKey(book.getPubId());
            bookVO.setBookType(bookType);
            bookVO.setPublisher(publisher);
            BookInRecordVO bookInRecordVO = new BookInRecordVO();
            BeanUtils.copyProperties(bookInRecord, bookInRecordVO);
            bookInRecordVO.setBookVO(bookVO);
            bookInRecordVOList.add(bookInRecordVO);
        }
    }

    //获取一次入库记录中所有书本的名字, 以逗号隔开
    private String getBookNames(List<BookInRecordVO> bookInRecordVOList) {
        if (bookInRecordVOList.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        for (BookInRecordVO br : bookInRecordVOList) {
            BookVO bookVO = br.getBookVO();
            result.append(bookVO.getBookName()).append(", ");
        }
        //去掉最后的逗号并返回
        return result.substring(0, result.lastIndexOf(","));
    }

    @Override
    public InRecordVO get(int id) {
        InRecord inRecord = inRecordMapper.selectByPrimaryKey(id);
        InRecordVO inRecordVO = new InRecordVO();
        BeanUtils.copyProperties(inRecord, inRecordVO);
        return processData(inRecordVO);
    }

    @Override
    public void save(InRecordVO inRecordVO) {
        inRecordMapper.insert(inRecordVO);
        for (BookInRecordVO bookInRecordVO : inRecordVO.getBookInRecordVOs()) {
            bookInRecordVO.setInRecordId(inRecordVO.getId());
            bookInRecordVO.setBookId(bookInRecordVO.getBookVO().getId());
            bookInRecordMapper.insert(bookInRecordVO);
            //修改书的库存
            int bookId = bookInRecordVO.getBookVO().getId();
            Book bookOld = bookMapper.selectByPrimaryKey(bookId);
            Book book4Update = new Book();
            book4Update.setId(bookId);
            book4Update.setRepertorySize(bookOld.getRepertorySize() + bookInRecordVO.getInSum());
            bookMapper.updateByPrimaryKeySelective(book4Update);
        }
    }

}
