package com.gmail.mosoft521.se.book.dao;

import com.gmail.mosoft521.se.book.entity.BookInRecord;
import com.gmail.mosoft521.se.book.entity.BookInRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInRecordMapper {
    long countByExample(BookInRecordExample example);

    int deleteByExample(BookInRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookInRecord record);

    int insertSelective(BookInRecord record);

    List<BookInRecord> selectByExample(BookInRecordExample example);

    BookInRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookInRecord record, @Param("example") BookInRecordExample example);

    int updateByExample(@Param("record") BookInRecord record, @Param("example") BookInRecordExample example);

    int updateByPrimaryKeySelective(BookInRecord record);

    int updateByPrimaryKey(BookInRecord record);
}