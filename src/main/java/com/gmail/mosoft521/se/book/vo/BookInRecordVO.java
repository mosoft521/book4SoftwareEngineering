package com.gmail.mosoft521.se.book.vo;

import com.gmail.mosoft521.se.book.entity.BookInRecord;

public class BookInRecordVO extends BookInRecord {
    //该记录所对应的书, 从数据库查出来时为null
    private BookVO bookVO;

    //该记录所对应的和库记录, 从数据库查出来时为null
    private InRecordVO inRecordVO;

    public BookInRecordVO() {
    }

    public BookVO getBookVO() {
        return bookVO;
    }

    public void setBookVO(BookVO bookVO) {
        this.bookVO = bookVO;
    }

    public InRecordVO getInRecordVO() {
        return inRecordVO;
    }

    public void setInRecordVO(InRecordVO inRecordVO) {
        this.inRecordVO = inRecordVO;
    }
}
