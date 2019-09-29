package com.gmail.mosoft521.se.book.dao;

import com.gmail.mosoft521.se.book.entity.InRecord;
import com.gmail.mosoft521.se.book.entity.InRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InRecordMapper {
    long countByExample(InRecordExample example);

    int deleteByExample(InRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InRecord record);

    int insertSelective(InRecord record);

    List<InRecord> selectByExample(InRecordExample example);

    InRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InRecord record, @Param("example") InRecordExample example);

    int updateByExample(@Param("record") InRecord record, @Param("example") InRecordExample example);

    int updateByPrimaryKeySelective(InRecord record);

    int updateByPrimaryKey(InRecord record);
}