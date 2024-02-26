package dao;

import bean.AllCountInfo;
import bean.BookCountInfo;

import java.sql.SQLException;
import java.util.List;

public interface IBookCountDao {
    /**
     * 统计各类型书籍数量
     */
    List<BookCountInfo> list(BookCountInfo book) throws SQLException;

    /**
     * 统计各类信息数量
     */
    List<AllCountInfo> list2(AllCountInfo bookCountInfo) throws SQLException;
}
