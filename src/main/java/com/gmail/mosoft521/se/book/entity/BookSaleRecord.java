package com.gmail.mosoft521.se.book.entity;

public class BookSaleRecord {
    private Integer id;

    private Integer bookId;

    private Integer saleRecordId;

    private Integer tradeSum;

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
}