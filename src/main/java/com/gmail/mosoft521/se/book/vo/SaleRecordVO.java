package com.gmail.mosoft521.se.book.vo;

import com.gmail.mosoft521.se.book.entity.SaleRecord;

import java.util.List;

public class SaleRecordVO extends SaleRecord {
    //销售的总数量
    private int amount;
    //总价钱
    private double totalPrice;
    //书的销售记录
    private List<BookSaleRecordVO> bookSaleRecordVOList;

    //该记录中对应所有书的名称, 显示用
    private String bookNames;

    public SaleRecordVO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BookSaleRecordVO> getBookSaleRecordVOList() {
        return bookSaleRecordVOList;
    }

    public void setBookSaleRecordVOList(List<BookSaleRecordVO> bookSaleRecordVOList) {
        this.bookSaleRecordVOList = bookSaleRecordVOList;
    }

    public String getBookNames() {
        return bookNames;
    }

    public void setBookNames(String bookNames) {
        this.bookNames = bookNames;
    }
}
