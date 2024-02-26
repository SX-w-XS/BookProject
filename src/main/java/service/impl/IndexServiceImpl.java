package service.impl;

import bean.AllCountInfo;
import bean.BookCountInfo;
import dao.IBookCountDao;
import dao.impl.BookCountDaoImpl;
import service.IndexService;

import java.sql.SQLException;
import java.util.List;

public class IndexServiceImpl implements IndexService {
    private IBookCountDao bookCountDao = new BookCountDaoImpl();

    @Override
    public List<BookCountInfo> list(BookCountInfo bookCountInfo) throws SQLException {
        return bookCountDao.list(bookCountInfo);
    }
    public List<AllCountInfo> list2(AllCountInfo AllCountInfo) throws SQLException {
        return bookCountDao.list2(AllCountInfo);
    }
}
