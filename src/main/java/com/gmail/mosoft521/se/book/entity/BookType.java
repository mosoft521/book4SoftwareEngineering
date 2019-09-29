package com.gmail.mosoft521.se.book.entity;

public class BookType {
    private Integer id;

    private String typeName;

    private String typeIntro;

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
}