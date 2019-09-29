package com.gmail.mosoft521.se.book.vo;

import com.gmail.mosoft521.se.book.entity.InRecord;

import java.util.Vector;

public class InRecordVO extends InRecord {
    //入库的总数量
    private int amount;

    //该入库记录所对应的书的入库记录
    private Vector<BookInRecordVO> bookInRecordVOs;

    //入库书的名称, 以逗号隔开
    private String bookNames;

    public InRecordVO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Vector<BookInRecordVO> getBookInRecordVOs() {
        return bookInRecordVOs;
    }

    public void setBookInRecordVOs(Vector<BookInRecordVO> bookInRecordVOs) {
        this.bookInRecordVOs = bookInRecordVOs;
    }

    public String getBookNames() {
        return bookNames;
    }

    public void setBookNames(String bookNames) {
        this.bookNames = bookNames;
    }
}
