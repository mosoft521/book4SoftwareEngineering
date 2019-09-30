package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.commons.BusinessException;
import com.gmail.mosoft521.se.book.commons.DateUtil;
import com.gmail.mosoft521.se.book.dao.BookMapper;
import com.gmail.mosoft521.se.book.dao.BookSaleRecordMapper;
import com.gmail.mosoft521.se.book.dao.SaleRecordMapper;
import com.gmail.mosoft521.se.book.entity.Book;
import com.gmail.mosoft521.se.book.entity.BookSaleRecord;
import com.gmail.mosoft521.se.book.entity.BookSaleRecordExample;
import com.gmail.mosoft521.se.book.entity.SaleRecord;
import com.gmail.mosoft521.se.book.entity.SaleRecordExample;
import com.gmail.mosoft521.se.book.service.SaleRecordService;
import com.gmail.mosoft521.se.book.vo.BookSaleRecordVO;
import com.gmail.mosoft521.se.book.vo.BookVO;
import com.gmail.mosoft521.se.book.vo.SaleRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售记录业务实现类
 */
@Service("saleRecordService")
@Transactional
public class SaleRecordServiceImpl implements SaleRecordService {
    @Autowired
    private SaleRecordMapper saleRecordMapper;

    @Autowired
    private BookSaleRecordMapper bookSaleRecordMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public SaleRecordVO get(int id) {
        SaleRecord saleRecord = saleRecordMapper.selectByPrimaryKey(id);
        SaleRecordVO saleRecordVO = new SaleRecordVO();
        BeanUtils.copyProperties(saleRecord, saleRecordVO);
        return processDatas(saleRecordVO);
    }

    @Override
    //实现接口方法
    public List<SaleRecordVO> getAll(Date date) {
        //得到下一天
        Date nextDate = DateUtil.getNextDate(date);
        SaleRecordExample saleRecordExample = new SaleRecordExample();
        SaleRecordExample.Criteria saleRecordExampleCriteria = saleRecordExample.createCriteria();
        saleRecordExampleCriteria.andRecordDateGreaterThanOrEqualTo(date);
        saleRecordExampleCriteria.andRecordDateLessThanOrEqualTo(nextDate);
        List<SaleRecord> records = saleRecordMapper.selectByExample(saleRecordExample);
        List<SaleRecordVO> saleRecordVOList = new ArrayList<>();
        for (SaleRecord saleRecord : records) {
            SaleRecordVO saleRecordVO = new SaleRecordVO();
            BeanUtils.copyProperties(saleRecord, saleRecordVO);
            processDatas(saleRecordVO);
            saleRecordVOList.add(saleRecordVO);
        }
        return saleRecordVOList;
    }

    //处理一个SaleRecord, 设置它的书本销售记录属性和书本名字属性
    private SaleRecordVO processDatas(SaleRecordVO saleRecordVO) {
        //查找该记录所对应的书的销售记录
        BookSaleRecordExample bookSaleRecordExample = new BookSaleRecordExample();
        BookSaleRecordExample.Criteria bookSaleRecordExampleCriteria = bookSaleRecordExample.createCriteria();
        bookSaleRecordExampleCriteria.andSaleRecordIdEqualTo(saleRecordVO.getId());
        List<BookSaleRecord> bookSaleRecordList = bookSaleRecordMapper.selectByExample(bookSaleRecordExample);
        List<BookSaleRecordVO> bookSaleRecordVOList = new ArrayList<>();
        for (BookSaleRecord bookSaleRecord : bookSaleRecordList) {
            BookSaleRecordVO bookSaleRecordVO = new BookSaleRecordVO();
            BeanUtils.copyProperties(bookSaleRecord, bookSaleRecordVO);
            bookSaleRecordVOList.add(bookSaleRecordVO);
        }
        //设置结果集中的每一个book属性
        setBook(bookSaleRecordVOList);
        //设置SaleRecord对象中的书的销售记录集合
        saleRecordVO.setBookSaleRecordVOList(bookSaleRecordVOList);
        //设置SaleRecord的书名集合
        saleRecordVO.setBookNames(getBookNames(bookSaleRecordVOList));
        //设置数量与总价
        saleRecordVO.setAmount(getAmount(bookSaleRecordVOList));
        saleRecordVO.setTotalPrice(getTotalPrice(bookSaleRecordVOList));
        return saleRecordVO;
    }

    //获取一次交易中涉及的总价
    private double getTotalPrice(List<BookSaleRecordVO> bookSaleRecordVOList) {
        double result = 0;
        for (BookSaleRecordVO bookSaleRecordVO : bookSaleRecordVOList) {
            //书本的交易量
            int tradeSum = bookSaleRecordVO.getTradeSum();
            //书的单价
            double bookPrice = bookSaleRecordVO.getBookVO().getBookPrice();
            result += (bookPrice * tradeSum);
        }
        return result;
    }

    //获取一次交易中所有书本的交易量
    private int getAmount(List<BookSaleRecordVO> bookSaleRecordVOList) {
        int result = 0;
        //遍历书的交易记录，计算总价
        for (BookSaleRecordVO bookSaleRecordVO : bookSaleRecordVOList) {
            result += bookSaleRecordVO.getTradeSum();
        }
        return result;
    }

    //设置参数中的每一个BookSaleRecord的book属性
    private void setBook(List<BookSaleRecordVO> bookSaleRecordVOList) {
        for (BookSaleRecordVO bookSaleRecordVO : bookSaleRecordVOList) {
            //调书本的数据访问层接口
            Book book = bookMapper.selectByPrimaryKey(bookSaleRecordVO.getBookId());
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            bookSaleRecordVO.setBookVO(bookVO);
        }
    }

    //获取一次交易中所有书本的名字, 以逗号隔开
    private String getBookNames(List<BookSaleRecordVO> bookSaleRecordVOList) {
        if (bookSaleRecordVOList.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        for (BookSaleRecordVO bookSaleRecordVO : bookSaleRecordVOList) {
            Book book = bookSaleRecordVO.getBookVO();
            result.append(book.getBookName()).append(", ");
        }
        //去掉最后的逗号并返回
        return result.substring(0, result.lastIndexOf(","));
    }


    @Override
    public void saveRecord(SaleRecordVO saleRecordVO) {
        //遍历判断书的库存是否不够
        for (BookSaleRecordVO bookSaleRecordVO : saleRecordVO.getBookSaleRecordVOList()) {
            int bookId = bookSaleRecordVO.getBookVO().getId();
            Book book = bookMapper.selectByPrimaryKey(bookId);
            //当存库不够时,抛出异常
            if (bookSaleRecordVO.getTradeSum() > book.getRepertorySize()) {
                throw new BusinessException(book.getBookName() + " 的库存不够");
            }
        }
        //先保存交易记录
        int id = saleRecordMapper.insert(saleRecordVO);
        //再保存书的交易记录
        for (BookSaleRecordVO bookSaleRecordVO : saleRecordVO.getBookSaleRecordVOList()) {
            //设置销售记录id
            bookSaleRecordVO.setSaleRecordId(id);
            bookSaleRecordMapper.insert(bookSaleRecordVO);
            //修改书的库存
            int bookId = bookSaleRecordVO.getBookVO().getId();
            Book bookOld = bookMapper.selectByPrimaryKey(bookId);
            //计算剩余的库存
            long leave = bookOld.getRepertorySize() - bookSaleRecordVO.getTradeSum();
            //设置库存并将库存数保存到数据库
            Book book4Update = new Book();
            book4Update.setId(bookId);
            book4Update.setRepertorySize(leave);
            bookMapper.updateByPrimaryKeySelective(book4Update);
        }
    }
}
