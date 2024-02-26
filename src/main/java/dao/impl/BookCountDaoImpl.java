package dao.impl;

import bean.AllCountInfo;
import bean.BookCountInfo;
import dao.IBookCountDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCountDaoImpl implements IBookCountDao {
    QueryRunner queryRunner;
    @Override
    public List<BookCountInfo> list(BookCountInfo book) throws SQLException {
        queryRunner = MyDBUtils.getQueryRunner();
        List<BookCountInfo> list = new ArrayList<>();
        BookCountInfo c =new BookCountInfo();
        //final int[] i = {0}; //保存各类型书籍数

        String sql0="select count(*) from b_books where N_Book_Type = ?";
        for(int i=0;i<=5;i++){
        queryRunner.query(sql0, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                while(rs.next()) {
                    BookCountInfo c =new BookCountInfo();
                    c.setCount(rs.getInt(1));
                    list.add(c);
                }
                return list;
            }
        },i);
        }
        return list;
    }

    @Override
    public List<AllCountInfo> list2(AllCountInfo bookCountInfo) throws SQLException {
        queryRunner = MyDBUtils.getQueryRunner();
        List<AllCountInfo> list = new ArrayList<>();
        AllCountInfo c =new AllCountInfo();

        String sql0="select count(*) from b_books where N_Book_Delete = ?";
        queryRunner.query(sql0, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                while(rs.next()) {
                    AllCountInfo c =new AllCountInfo();
                    c.setCount(rs.getInt(1));
                    c.setName("书本数:");
                    list.add(c);
                }
                return list;
            }
        },1);


        String sql1 = "select count(*) from b_reader where N_Reader_IsDeleted = ?";
        queryRunner.query(sql1, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                while(rs.next()) {
                    AllCountInfo c = new AllCountInfo();
                    c.setCount(rs.getInt(1));
                    c.setName("用户数:");
                    list.add(c);
                }
                return list;
            }
        },1);

        String sql2 = "select count(*) from b_message";
        queryRunner.query(sql2, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                while(rs.next()) {
                    AllCountInfo c = new AllCountInfo();
                    c.setCount(rs.getInt(1));
                    c.setName("消息数:");
                    list.add(c);
                }
                return list;
            }
        });

        String sql3 = "select count(*) from b_discuss where N_Discuss_Isdeleted = ?";
        queryRunner.query(sql3, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                while(rs.next()) {
                    AllCountInfo c = new AllCountInfo();
                    c.setCount(rs.getInt(1));
                    c.setName("评论数:");
                    list.add(c);
                }
                return list;
            }
        },1);

        String sql4 = "select count(*) from b_commodity";
        queryRunner.query(sql4, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                while(rs.next()) {
                    AllCountInfo c = new AllCountInfo();
                    c.setCount(rs.getInt(1));
                    c.setName("商品数:");
                    list.add(c);
                }
                return list;
            }
        });
        return list;
    }
}