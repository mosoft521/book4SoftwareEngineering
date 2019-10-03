package com.gmail.mosoft521.se.book.entity;

import java.util.Date;

public class BookSaleRecord {
    private Integer id;

    private Integer bookId;

    private Integer saleRecordId;

    private Integer tradeSum;

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

    public Integer getSaleRecordId() {
        return saleRecordId;
    }

    public void setSaleRecordId(Integer saleRecordId) {
        this.saleRecordId = saleRecordId;
    }

    public Integer getTradeSum() {
        return tradeSum;
    }

    public void setTradeSum(Integer tradeSum) {
        this.tradeSum = tradeSum;
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