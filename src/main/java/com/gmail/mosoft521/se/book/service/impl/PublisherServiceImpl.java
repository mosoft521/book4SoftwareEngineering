package com.gmail.mosoft521.se.book.service.impl;

import com.gmail.mosoft521.se.book.dao.PublisherMapper;
import com.gmail.mosoft521.se.book.entity.Publisher;
import com.gmail.mosoft521.se.book.entity.PublisherExample;
import com.gmail.mosoft521.se.book.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 出版社业务实现类
 */
@Service("publisherService")
@Transactional
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public List<Publisher> getAll() {
        return publisherMapper.selectByExample(null);
    }

    @Override
    public Publisher find(int id) {
        return publisherMapper.selectByPrimaryKey(id);
    }

    @Override
    public Publisher add(Publisher publisher) {
        publisherMapper.insert(publisher);
        return find(publisher.getId());
    }

    @Override
    public Publisher update(Publisher publisher) {
        //调用DAO方法修改对象
        int id = publisherMapper.updateByPrimaryKeySelective(publisher);
        //重新查找该对象
        return find(id);
    }

    @Override
    public List<Publisher> query(String pubName) {
        PublisherExample publisherExample = new PublisherExample();
        PublisherExample.Criteria publisherExampleCriteria = publisherExample.createCriteria();
        publisherExampleCriteria.andPubNameLike("%" + pubName + "%");
        return publisherMapper.selectByExample(publisherExample);
    }
}
