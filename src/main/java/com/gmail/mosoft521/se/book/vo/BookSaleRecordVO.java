package com.gmail.mosoft521.se.book.vo;

import com.gmail.mosoft521.se.book.entity.BookSaleRecord;

public class BookSaleRecordVO extends BookSaleRecord {
    //该记录对应的书对象, 当从数据库查找到BookSaleRecord时, 该属性为null
    private BookVO bookVO;

    //该记录对应的销售记录对象, 当从数据库查找到BookSaleRecord时, 该属性为null
    private SaleRecordVO saleRecordVO;

    public BookSaleRecordVO() {
    }

    public BookVO getBookVO() {
        return bookVO;
    }

    public void setBookVO(BookVO bookVO) {
        this.bookVO = bookVO;
    }

    public SaleRecordVO getSaleRecordVO() {
        return saleRecordVO;
    }

    public void setSaleRecordVO(SaleRecordVO saleRecordVO) {
        this.saleRecordVO = saleRecordVO;
    }
}
