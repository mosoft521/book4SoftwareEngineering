package com.gmail.mosoft521.se.book.service;

import com.gmail.mosoft521.se.book.entity.SaleRecord;

import java.util.Collection;
import java.util.Date;

/**
 * 销售业务接口
 */
public interface SaleRecordService {

    /**
     * 新增一条销售记录
     *
     * @param record
     */
    void saveRecord(SaleRecord record);

    /**
     * 根据日期获取该日期对应的销售记录
     *
     * @param date
     * @return
     */
    Collection<SaleRecord> getAll(Date date);

    /**
     * 根据id获取销售记录
     *
     * @param id
     * @return
     */
    SaleRecord get(int id);

}
