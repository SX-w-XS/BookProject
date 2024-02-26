package service.impl;

import bean.BookInfo;
import dao.IBookDao;
import dao.impl.BookDaoImpl;
import service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    IBookDao bookDao = new BookDaoImpl();
    @Override
    public List<BookInfo> queryByState(int code) {
        return bookDao.queryByState(code);
    }

    @Override
    public List<BookInfo> list(BookInfo book) {
        return bookDao.list(book);
    }
    @Override
    public Integer saveBook(BookInfo book) {
        return bookDao.saveBook(book);
    }
    @Override
    public Integer updateBook(BookInfo book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Integer deleteById(Integer id) {
        return bookDao.deleteById(id);
    }
    @Override
    public BookInfo queryById(Integer id) {
        return bookDao.queryById(id);
    }
    @Override
    public List<BookInfo> listByBookName(BookInfo book, String name) {
        return bookDao.listByBookName(book,name);
    }
    @Override
    public List<BookInfo> listByBookType(BookInfo book, Integer type) {
        return bookDao.listByBookType(book,type);
    }
}
