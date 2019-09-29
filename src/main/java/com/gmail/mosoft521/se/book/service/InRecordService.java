package com.gmail.mosoft521.se.book.service;


import com.gmail.mosoft521.se.book.entity.InRecord;

import java.util.Collection;
import java.util.Date;

/**
 * 入库记录业务接口
 */
public interface InRecordService {

    /**
     * 保存一条入库记录
     *
     * @param r
     */
    void save(InRecord r);

    /**
     * 根据日期查找对应的入库记录
     *
     * @param date
     * @return
     */
    Collection<InRecord> getAll(Date date);

    /**
     * 根据id获得入库记录
     *
     * @param id
     * @return
     */
    InRecord get(int id);
}
