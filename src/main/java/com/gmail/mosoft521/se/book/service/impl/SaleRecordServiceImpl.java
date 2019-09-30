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
        //得到今天的日期, 格式为yyyy-MM-dd
        String today = DateUtil.getDateString(date);
        //得到明天的日期, 格式为yyyy-MM-dd
        String tomorrow = DateUtil.getDateString(nextDate);
        SaleRecordExample saleRecordExample = new SaleRecordExample();
        SaleRecordExample.Criteria saleRecordExampleCriteria = saleRecordExample.createCriteria();
        saleRecordExampleCriteria.andRecordDateGreaterThanOrEqualTo(date);
        saleRecordExampleCriteria.andRecordDateLessThanOrEqualTo(nextDate);
        List<SaleRecord> records = saleRecordMapper.selectByExample(saleRecordExample);
        List<SaleRecordVO> saleRecordVOList = new ArrayList<>();
        for (SaleRecord r : records) {
            SaleRecordVO saleRecordVO = new SaleRecordVO();
            BeanUtils.copyProperties(r, saleRecordVO);
            processDatas(saleRecordVO);
            saleRecordVOList.add(saleRecordVO);
        }
        return saleRecordVOList;
    }

    //处理一个SaleRecord, 设置它的书本销售记录属性和书本名字属性
    private SaleRecordVO processDatas(SaleRecordVO r) {
        //查找该记录所对应的书的销售记录
//        BookSaleRecordExample
        BookSaleRecordExample bookSaleRecordExample = new BookSaleRecordExample();
        BookSaleRecordExample.Criteria bookSaleRecordExampleCriteria = bookSaleRecordExample.createCriteria();
        bookSaleRecordExampleCriteria.andSaleRecordIdEqualTo(r.getId());
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
        r.setBookSaleRecordVOList(bookSaleRecordVOList);
        //设置SaleRecord的书名集合
        r.setBookNames(getBookNames(bookSaleRecordVOList));
        //设置数量与总价
        r.setAmount(getAmount(bookSaleRecordVOList));
        r.setTotalPrice(getTotalPrice(bookSaleRecordVOList));
        return r;
    }

    //获取一次交易中涉及的总价
    private double getTotalPrice(List<BookSaleRecordVO> brs) {
        double result = 0;
        for (BookSaleRecordVO br : brs) {
            //书本的交易量
            int tradeSum = Integer.valueOf(br.getTradeSum());
            //书的单价
            double bookPrice = Double.valueOf(br.getBookVO().getBookPrice());
            result += (bookPrice * tradeSum);
        }
        return result;
    }

    //获取一次交易中所有书本的交易量
    private int getAmount(List<BookSaleRecordVO> brs) {
        int result = 0;
        //遍历书的交易记录，计算总价
        for (BookSaleRecordVO br : brs) {
            result += Integer.valueOf(br.getTradeSum());
        }
        return result;
    }

    //设置参数中的每一个BookSaleRecord的book属性
    private void setBook(List<BookSaleRecordVO> brs) {
        for (BookSaleRecordVO br : brs) {
            //调书本的数据访问层接口
            Book book = bookMapper.selectByPrimaryKey(br.getBookId());
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            br.setBookVO(bookVO);
        }
    }

    //获取一次交易中所有书本的名字, 以逗号隔开
    private String getBookNames(List<BookSaleRecordVO> brs) {
        if (brs.size() == 0) return "";
        StringBuffer result = new StringBuffer();
        for (BookSaleRecordVO br : brs) {
            Book book = br.getBookVO();
            result.append(book.getBookName() + ", ");
        }
        //去掉最后的逗号并返回
        return result.substring(0, result.lastIndexOf(","));
    }


    @Override
    public void saveRecord(SaleRecordVO record) {
        //遍历判断书的库存是否不够
        for (BookSaleRecordVO r : record.getBookSaleRecordVOList()) {
            int bookId = r.getBookVO().getId();
            Book b = bookMapper.selectByPrimaryKey(bookId);
            //当存库不够时,抛出异常
            if (Integer.valueOf(r.getTradeSum()) >
                    b.getRepertorySize()) {
                throw new BusinessException(b.getBookName() + " 的库存不够");
            }
        }
        //先保存交易记录
        int id = saleRecordMapper.insert(record);
        //再保存书的交易记录
        for (BookSaleRecordVO saleRecordVO : record.getBookSaleRecordVOList()) {
            //设置销售记录id
            saleRecordVO.setSaleRecordId(id);
            bookSaleRecordMapper.insert(saleRecordVO);
            //修改书的库存
            int bookId = saleRecordVO.getBookVO().getId();
            Book bookOld = bookMapper.selectByPrimaryKey(bookId);
            //计算剩余的库存
            long leave = bookOld.getRepertorySize() -
                    saleRecordVO.getTradeSum();
            //设置库存并将库存数保存到数据库
            Book book4Update = new Book();
            book4Update.setId(bookId);
            book4Update.setRepertorySize(leave);
            bookMapper.updateByPrimaryKeySelective(book4Update);
        }
    }
}
