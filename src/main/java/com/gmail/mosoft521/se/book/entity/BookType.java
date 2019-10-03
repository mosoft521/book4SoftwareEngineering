package com.gmail.mosoft521.se.book.entity;

import java.util.Date;

public class BookType {
    private Integer id;

    private String typeName;

    private String typeIntro;

    private Date createTime;

    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeIntro() {
        return typeIntro;
    }

    public void setTypeIntro(String typeIntro) {
        this.typeIntro = typeIntro == null ? null : typeIntro.trim();
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