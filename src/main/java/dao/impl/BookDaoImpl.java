package dao.impl;

import bean.BookInfo;
import dao.IBookDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DelFlagE;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao{
    String sql;
    QueryRunner queryRunner;
    @Override
    public List<BookInfo> queryByState(int state) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_books where N_Book_State=?";
        List<BookInfo> list =null;
        try {
            list = queryRunner.query(sql, new ResultSetHandler<List<BookInfo>>() {
                @Override
                public List<BookInfo> handle(ResultSet rs) throws SQLException {
                    List<BookInfo> list = new ArrayList<>();
                    while(rs.next()){
                        BookInfo bookInfo = new BookInfo();
                        bookInfo.setbId(rs.getInt("N_Book_Id"));
                        bookInfo.setbName(rs.getNString("VC_Book_Name"));
                        bookInfo.setAuthor(rs.getString("VC_Book_Author"));
                        bookInfo.setPublish(rs.getString("VC_Book_Publish"));
                        bookInfo.setIsbn(rs.getString("VC_Book_Isbn"));
                        bookInfo.setIntroduction(rs.getString("VC_Book_Introduction"));
                        bookInfo.setPrice(rs.getFloat("F_Book_Price"));
                        bookInfo.setPudate(rs.getDate("D_Book_Pubdate"));
                        bookInfo.setPressmark(rs.getInt("VC_Book_Pressmark"));
                        bookInfo.setState(rs.getInt("N_Book_State"));
                        bookInfo.setType(rs.getInt("N_Book_Type"));
                        list.add(bookInfo);
                    }
                    return list;
                }
            },state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<BookInfo> list(BookInfo book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_books where N_Book_Delete = ?";
        List<BookInfo> list =null;
        try {
            list = queryRunner.query(sql, new ResultSetHandler<List<BookInfo>>() {
                @Override
                public List<BookInfo> handle(ResultSet rs) throws SQLException {
                    List<BookInfo> list = new ArrayList<>();
                    while (rs.next()) {
                        BookInfo b = new BookInfo();
                        b.setbId(rs.getInt("N_Book_Id"));
                        b.setbName(rs.getNString("VC_Book_Name"));
                        b.setAuthor(rs.getString("VC_Book_Author"));
                        b.setPublish(rs.getString("VC_Book_Publish"));
                        b.setIsbn(rs.getString("VC_Book_Isbn"));
                        b.setIntroduction(rs.getString("VC_Book_Introduction"));
                        b.setPrice(rs.getFloat("F_Book_Price"));
                        b.setPudate(rs.getDate("D_Book_Pubdate"));
                        b.setState(rs.getInt("N_Book_State"));
                        b.setPressmark(rs.getInt("VC_Book_Pressmark"));
                        b.setType(rs.getInt("N_Book_Type"));
                        list.add(b);
                    }
                    return list;
                }
                }, DelFlagE.NO.code);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return list;
    }
    @Override
    public Integer saveBook(BookInfo book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "insert into b_books(VC_Book_Name,VC_Book_Author,VC_Book_Publish,VC_Book_Isbn,VC_Book_Introduction,F_Book_Price,D_Book_Pubdate,VC_Book_Pressmark,N_Book_State,N_Book_Type)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, book.getbName(),
                    book.getAuthor(),
                    book.getPublish(),
                    book.getIsbn(),
                    book.getIntroduction(),
                    book.getPrice(),
                    book.getPudate(),
                    book.getPressmark(),
                    book.getState(),
                    book.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public Integer updateBook(BookInfo book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update b_books set VC_Book_Name = ?,VC_Book_Author = ?,VC_Book_Publish = ? ,VC_Book_Isbn = ?" +
                ",VC_Book_Introduction = ?,F_Book_Price = ?,D_Book_Pubdate = ?,VC_Book_Pressmark = ?," +
                "N_Book_State = ?,N_Book_Type = ? where N_Book_Id = ?";
        try {
            return queryRunner.update(sql, book.getbName(),
                    book.getAuthor(),
                    book.getPublish(),
                    book.getIsbn(),
                    book.getIntroduction(),
                    book.getPrice(),
                    book.getPudate(),
                    book.getPressmark(),
                    book.getState(),
                    book.getType(),
                    book.getbId());
        }catch (SQLException e){
            e.printStackTrace();}
        return -1;
    }
    @Override
    public Integer deleteById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update b_books set N_Book_Delete = ? where N_Book_Id = ?";
        try{
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public BookInfo queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_books where N_Book_Delete = ? and N_Book_Id = ?";
        List<BookInfo> list =null;
        try {
            return queryRunner.query(sql, new ResultSetHandler<BookInfo>() {
                @Override
                public BookInfo handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        BookInfo b = new BookInfo();
                        b.setbId(rs.getInt("N_Book_Id"));
                        b.setbName(rs.getNString("VC_Book_Name"));
                        b.setAuthor(rs.getString("VC_Book_Author"));
                        b.setPublish(rs.getString("VC_Book_Publish"));
                        b.setIsbn(rs.getString("VC_Book_Isbn"));
                        b.setIntroduction(rs.getString("VC_Book_Introduction"));
                        b.setPrice(rs.getFloat("F_Book_Price"));
                        b.setPudate(rs.getDate("D_Book_Pubdate"));
                        b.setPressmark(rs.getInt("VC_Book_Pressmark"));
                        b.setState(rs.getInt("N_Book_State"));
                        b.setType(rs.getInt("N_Book_Type"));
                        return b;
                    }
                    return null;
                }
                }, DelFlagE.NO.code,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<BookInfo> listByBookName(BookInfo book,String name) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_books where N_Book_Delete = ? and VC_Book_Name = ?";
        List<BookInfo> list =null;
        try {
            list = queryRunner.query(sql, new ResultSetHandler<List<BookInfo>>() {
                @Override
                public List<BookInfo> handle(ResultSet rs) throws SQLException {
                    List<BookInfo> list = new ArrayList<>();
                    while(rs.next()){
                        BookInfo b = new BookInfo();
                        b.setbId(rs.getInt("N_Book_Id"));
                        b.setbName(rs.getNString("VC_Book_Name"));
                        b.setAuthor(rs.getString("VC_Book_Author"));
                        b.setPublish(rs.getString("VC_Book_Publish"));
                        b.setIsbn(rs.getString("VC_Book_Isbn"));
                        b.setIntroduction(rs.getString("VC_Book_Introduction"));
                        b.setPrice(rs.getFloat("F_Book_Price"));
                        b.setPudate(rs.getDate("D_Book_Pubdate"));
                        b.setPressmark(rs.getInt("VC_Book_Pressmark"));
                        b.setState(rs.getInt("N_Book_State"));
                        b.setType(rs.getInt("N_Book_Type"));
                        list.add(b);
                    }
                    return list;
                }
                }, DelFlagE.NO.code,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<BookInfo> listByBookType(BookInfo book,Integer type) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_books where N_Book_Delete = ? and N_Book_Type = ?";
        List<BookInfo> list =null;
        try {
            list = queryRunner.query(sql, new ResultSetHandler<List<BookInfo>>() {
                public List<BookInfo> handle(ResultSet rs) throws SQLException {
                    List<BookInfo> list = new ArrayList<>();
                    while (rs.next()) {
                        BookInfo b = new BookInfo();
                        b.setbId(rs.getInt("N_Book_Id"));
                        b.setbName(rs.getNString("VC_Book_Name"));
                        b.setAuthor(rs.getString("VC_Book_Author"));
                        b.setPublish(rs.getString("VC_Book_Publish"));
                        b.setIsbn(rs.getString("VC_Book_Isbn"));
                        b.setIntroduction(rs.getString("VC_Book_Introduction"));
                        b.setPrice(rs.getFloat("F_Book_Price"));
                        b.setPudate(rs.getDate("D_Book_Pubdate"));
                        b.setPressmark(rs.getInt("VC_Book_Pressmark"));
                        b.setState(rs.getInt("N_Book_State"));
                        b.setType(rs.getInt("N_Book_Type"));
                        list.add(b);
                    }
                    return list;
                }
                }, DelFlagE.NO.code,type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}