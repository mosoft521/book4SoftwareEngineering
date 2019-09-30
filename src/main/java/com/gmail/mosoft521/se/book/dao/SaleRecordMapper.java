package com.gmail.mosoft521.se.book.dao;

import com.gmail.mosoft521.se.book.entity.SaleRecord;
import com.gmail.mosoft521.se.book.entity.SaleRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRecordMapper {
    long countByExample(SaleRecordExample example);

    int deleteByExample(SaleRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleRecord record);

    int insertSelective(SaleRecord record);

    List<SaleRecord> selectByExample(SaleRecordExample example);

    SaleRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleRecord record, @Param("example") SaleRecordExample example);

    int updateByExample(@Param("record") SaleRecord record, @Param("example") SaleRecordExample example);

    int updateByPrimaryKeySelective(SaleRecord record);

    int updateByPrimaryKey(SaleRecord record);
}