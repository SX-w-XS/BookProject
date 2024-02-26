package service;

import bean.AllCountInfo;
import bean.BookCountInfo;

import java.sql.SQLException;
import java.util.List;

public interface IndexService {

    List<BookCountInfo> list(BookCountInfo bookCountInfo) throws SQLException;
    List<AllCountInfo> list2(AllCountInfo bookCountInfo) throws SQLException;
}
