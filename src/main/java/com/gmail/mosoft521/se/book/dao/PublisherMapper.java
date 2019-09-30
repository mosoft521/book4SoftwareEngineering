package com.gmail.mosoft521.se.book.dao;

import com.gmail.mosoft521.se.book.entity.Publisher;
import com.gmail.mosoft521.se.book.entity.PublisherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherMapper {
    long countByExample(PublisherExample example);

    int deleteByExample(PublisherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Publisher record);

    int insertSelective(Publisher record);

    List<Publisher> selectByExample(PublisherExample example);

    Publisher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Publisher record, @Param("example") PublisherExample example);

    int updateByExample(@Param("record") Publisher record, @Param("example") PublisherExample example);

    int updateByPrimaryKeySelective(Publisher record);

    int updateByPrimaryKey(Publisher record);
}