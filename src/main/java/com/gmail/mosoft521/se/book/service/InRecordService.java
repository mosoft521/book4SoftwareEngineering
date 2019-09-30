package com.gmail.mosoft521.se.book.service;


import com.gmail.mosoft521.se.book.vo.InRecordVO;

import java.util.Date;
import java.util.List;

/**
 * 入库记录业务接口
 */
public interface InRecordService {

    /**
     * 保存一条入库记录
     *
     * @param inRecordVO
     */
    void save(InRecordVO inRecordVO);

    /**
     * 根据日期查找对应的入库记录
     *
     * @param date
     * @return
     */
    List<InRecordVO> getAll(Date date);

    /**
     * 根据id获得入库记录
     *
     * @param id
     * @return
     */
    InRecordVO get(int id);
}
