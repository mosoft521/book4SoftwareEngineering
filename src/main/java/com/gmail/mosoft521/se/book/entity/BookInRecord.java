package com.gmail.mosoft521.se.book.entity;

import java.util.Date;

public class BookInRecord {
    private Integer id;

    private Integer bookId;

    private Integer inRecordId;

    private Integer inSum;

    private Date createTime;

    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getInRecordId() {
        return inRecordId;
    }

    public void setInRecordId(Integer inRecordId) {
        this.inRecordId = inRecordId;
    }

    public Integer getInSum() {
        return inSum;
    }

    public void setInSum(Integer inSum) {
        this.inSum = inSum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
}