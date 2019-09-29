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
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

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
    public Collection<InRecordVO> getAll(Date date) {
        //得到下一天
        Date nextDate = DateUtil.getNextDate(date);
        //得到今天的日期, 格式为yyyy-MM-dd
        String today = DateUtil.getDateString(date);
        //得到明天的日期, 格式为yyyy-MM-dd
        String tomorrow = DateUtil.getDateString(nextDate);
        InRecordExample inRecordExample = new InRecordExample();
        InRecordExample.Criteria inRecordExampleCriteria = inRecordExample.createCriteria();
        inRecordExampleCriteria.andRecordDateGreaterThanOrEqualTo(date);
        inRecordExampleCriteria.andRecordDateLessThanOrEqualTo(nextDate);
        Collection<InRecord> records = inRecordMapper.selectByExample(inRecordExample);
        Collection<InRecordVO> inRecordVOs = new ArrayList<>();
        for (InRecord r : records) {
            InRecordVO InRecordVO = new InRecordVO();
            BeanUtils.copyProperties(r, InRecordVO);
            processData(InRecordVO);
        }
        return inRecordVOs;
    }

    private InRecord processData(InRecordVO inRecordVO) {
        BookInRecordExample bookInRecordExample = new BookInRecordExample();
        BookInRecordExample.Criteria bookInRecordExampleCriteria = bookInRecordExample.createCriteria();
        bookInRecordExampleCriteria.andInRecordIdEqualTo(inRecordVO.getId());
        Collection<BookInRecord> bookInRecords = bookInRecordMapper.selectByExample(bookInRecordExample);
        Collection<BookInRecordVO> bookInRecordVOs = new ArrayList<>();
        //设置记录中的每一本书
        setBook(bookInRecords, bookInRecordVOs);
        //设置入库记录中的书的入库记录
        inRecordVO.setBookInRecordVOs((Vector<BookInRecordVO>) bookInRecordVOs);
        //设置书名
        inRecordVO.setBookNames(getBookNames(bookInRecordVOs));
        //设置书总数
        inRecordVO.setAmount(getAmount(bookInRecordVOs));
        return inRecordVO;
    }

    //获取一次入库记录中所有书本的交易量
    private int getAmount(Collection<BookInRecordVO> birs) {
        int result = 0;
        for (BookInRecord br : birs) {
            result += Integer.valueOf(br.getInSum());
        }
        return result;
    }

    //设置参数中的每一个BookInRecord的book属性
    private void setBook(Collection<BookInRecord> bookInRecords, Collection<BookInRecordVO> bookInRecordVOs) {
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
        }
    }

    //获取一次入库记录中所有书本的名字, 以逗号隔开
    private String getBookNames(Collection<BookInRecordVO> birs) {
        if (birs.size() == 0) return "";
        StringBuffer result = new StringBuffer();
        for (BookInRecordVO br : birs) {
            BookVO bookVO = br.getBookVO();
            result.append(bookVO.getBookName() + ", ");
        }
        //去掉最后的逗号并返回
        return result.substring(0, result.lastIndexOf(","));
    }

    @Override
    public InRecord get(int id) {
        InRecord r = inRecordMapper.selectByPrimaryKey(id);
        InRecordVO InRecordVO = new InRecordVO();
        BeanUtils.copyProperties(r, InRecordVO);
        return processData(InRecordVO);
    }

    @Override
    public void save(InRecordVO inRecordVO) {
        int id = inRecordMapper.insert(inRecordVO);
        for (BookInRecordVO br : inRecordVO.getBookInRecordVOs()) {
            br.setInRecordId(id);
            bookInRecordMapper.insert(br);
            //修改书的库存
            int bookId = br.getBookVO().getId();
            Book b = bookMapper.selectByPrimaryKey(bookId);
            b.setRepertorySize(Long.valueOf(b.getRepertorySize() + br.getInSum()));
            /*bookMapper.updateByExample(b);//todo:*/
        }
    }

}
