package service;

import bean.BookInfo;

import java.util.List;

public interface IBookService {
    List<BookInfo> queryByState(int code);

    List<BookInfo> list(BookInfo book);
    Integer saveBook(BookInfo book);
    Integer updateBook(BookInfo book);

    Integer deleteById(Integer id);
    BookInfo queryById(Integer id);
    List<BookInfo> listByBookName(BookInfo book,String name);
    List<BookInfo> listByBookType(BookInfo book,Integer type);
}
