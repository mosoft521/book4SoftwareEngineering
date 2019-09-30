package com.gmail.mosoft521.se.book.dao;

import com.gmail.mosoft521.se.book.entity.BookSaleRecord;
import com.gmail.mosoft521.se.book.entity.BookSaleRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSaleRecordMapper {
    long countByExample(BookSaleRecordExample example);

    int deleteByExample(BookSaleRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookSaleRecord record);

    int insertSelective(BookSaleRecord record);

    List<BookSaleRecord> selectByExample(BookSaleRecordExample example);

    BookSaleRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookSaleRecord record, @Param("example") BookSaleRecordExample example);

    int updateByExample(@Param("record") BookSaleRecord record, @Param("example") BookSaleRecordExample example);

    int updateByPrimaryKeySelective(BookSaleRecord record);

    int updateByPrimaryKey(BookSaleRecord record);
}