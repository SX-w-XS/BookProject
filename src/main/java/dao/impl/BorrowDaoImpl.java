package dao.impl;

import bean.BorrowInfo;
import dao.IBorrowDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.BookFlagE;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BorrowDaoImpl implements IBorrowDao {
    @Override
    public Integer updateBookStateBorrow(String bookName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_books set N_Book_State=? where VC_Book_Name=?";
        try {
            return queryRunner.update(sql, BookFlagE.BORROW.code, bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer insertBorrowInfo(BorrowInfo borrow) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into b_borrow(VC_Borrow_ReaderName,VC_Borrow_BookName,D_Borrow_Botime,D_Borrow_Bctime,N_Borrow_State)values(?,?,?,?,?)";
        try {
            return queryRunner.update(sql,
                    borrow.getReaderName(),
                    borrow.getBookName(),
                    borrow.getBorrowTime(),
                    borrow.getBackTime(),
                    borrow.getBorrowState()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<BorrowInfo> list(BorrowInfo borrow) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_borrow";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<BorrowInfo>>() {
                @Override
                public List<BorrowInfo> handle(ResultSet rs) throws SQLException {
                    List<BorrowInfo> list = new ArrayList<>();
                    while (rs.next()) {
                        BorrowInfo borrow = new BorrowInfo();
                        borrow.setId(rs.getInt("N_Borrow_Id"));
                        borrow.setReaderName(rs.getString("VC_Borrow_ReaderName"));
                        borrow.setBookName(rs.getString("VC_Borrow_BookName"));
                        borrow.setBorrowTime(rs.getString("D_Borrow_Botime"));
                        borrow.setBackTime(rs.getString("D_Borrow_Bctime"));
                        borrow.setBorrowState(rs.getInt("N_Borrow_State"));
                        list.add(borrow);
                    }
                    return list;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateBorrowInfo(BorrowInfo borrow) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_borrow set D_Borrow_Bctime=?, N_Borrow_State=? where VC_Borrow_BookName=?";
        try {
            return queryRunner.update(sql,
                    borrow.getBackTime(),
                    borrow.getBorrowState(),
                    borrow.getBookName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public BorrowInfo queryBorrowByBookName(String bookName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_borrow where VC_Borrow_BookName=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<BorrowInfo>() {
                @Override
                public BorrowInfo handle(ResultSet rs) throws SQLException {
                    if (rs.next()) {
                        BorrowInfo borrow = new BorrowInfo();
                        borrow.setId(rs.getInt("N_Borrow_Id"));
                        borrow.setReaderName(rs.getString("VC_Borrow_ReaderName"));
                        borrow.setBookName(rs.getString("VC_Borrow_BookName"));
                        borrow.setBorrowTime(rs.getString("D_Borrow_Botime"));
                        borrow.setBackTime(rs.getString("D_Borrow_Bctime"));
                        borrow.setBorrowState(rs.getInt("N_Borrow_State"));
                        return borrow;
                    }
                    return null;
                }
            }, bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateBookStateFree(String bookName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_books set N_Book_State=? where VC_Book_Name=?";
        try {
            return queryRunner.update(sql, BookFlagE.FREE.code, bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<BorrowInfo> listByReaderName(String readerName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_borrow where VC_Borrow_ReaderName=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<BorrowInfo>>() {
                @Override
                public List<BorrowInfo> handle(ResultSet rs) throws SQLException {
                    List<BorrowInfo> list = new ArrayList<>();
                    while (rs.next()) {
                        BorrowInfo borrow = new BorrowInfo();
                        borrow.setId(rs.getInt("N_Borrow_Id"));
                        borrow.setReaderName(rs.getString("VC_Borrow_ReaderName"));
                        borrow.setBookName(rs.getString("VC_Borrow_BookName"));
                        borrow.setBorrowTime(rs.getString("D_Borrow_Botime"));
                        borrow.setBackTime(rs.getString("D_Borrow_Bctime"));
                        borrow.setBorrowState(rs.getInt("N_Borrow_State"));
                        list.add(borrow);
                    }
                    return list;
                }
            }, readerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateReadCount(Integer count, String readerName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_readcard set N_Reader_Count=? where VC_ReadCard_ReadName=?";
        try {
            return queryRunner.update(sql, count, readerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public Integer countReadCount(String readerName) {
        final Integer[] count = {0};
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql1 = "select * from b_borrow where N_Borrow_State=? and VC_Borrow_ReaderName=?";
        try {
            queryRunner.query(sql1, new ResultSetHandler() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    while(rs.next()){
                        count[0]++;
                    }
                    return null;
                }
            }, 0, readerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count[0];
    }
}