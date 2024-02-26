package dao;

import bean.BookInfo;

import java.util.List;

public interface IBookDao {
    List<BookInfo> queryByState(int code);
    /**
     *查询所有的书籍信息
     */
    public List<BookInfo> list(BookInfo book);

    /**
     *添加新的书籍信息
     */
    Integer saveBook(BookInfo book);

    /**
     *更新书籍信息
     */
    Integer updateBook(BookInfo book);

    /**
     *按照编号删除书籍信息
     */
    Integer deleteById(Integer id);

    BookInfo queryById(Integer id);
    List<BookInfo> listByBookName(BookInfo book,String name);
    public List<BookInfo> listByBookType(BookInfo book,Integer type);
}
