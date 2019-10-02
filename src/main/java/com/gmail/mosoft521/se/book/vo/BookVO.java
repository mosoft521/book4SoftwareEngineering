package com.gmail.mosoft521.se.book.vo;

import com.gmail.mosoft521.se.book.entity.Book;
import com.gmail.mosoft521.se.book.entity.BookType;
import com.gmail.mosoft521.se.book.entity.Publisher;

public class BookVO extends Book {
    //书本种类，从数据库查询出来的时候，这个属性为null，再通过本类的TYPE_ID_FK去设置这个属性
    private BookType bookType;

    //书对应的出版社，与type相同
    private Publisher publisher;

    public BookVO() {
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BookVO) {
            BookVO b = (BookVO) obj;
            return getId().equals(b.getId());
        }
        return false;
    }

    public String toString() {
        return getBookName();
    }
}
