package com.gmail.mosoft521.se.book.service;

import com.gmail.mosoft521.se.book.vo.SaleRecordVO;

import java.util.Date;
import java.util.List;

/**
 * 销售业务接口
 */
public interface SaleRecordService {

    /**
     * 新增一条销售记录
     *
     * @param saleRecordVO
     */
    void saveRecord(SaleRecordVO saleRecordVO);

    /**
     * 根据日期获取该日期对应的销售记录
     *
     * @param date
     * @return
     */
    List<SaleRecordVO> getAll(Date date);

    /**
     * 根据id获取销售记录
     *
     * @param id
     * @return
     */
    SaleRecordVO get(int id);

}
