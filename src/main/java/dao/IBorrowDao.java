package dao;

import bean.BorrowInfo;

import java.util.List;

public interface IBorrowDao {
    Integer updateBookStateBorrow(String bookName);
    Integer insertBorrowInfo(BorrowInfo borrow);

    List<BorrowInfo> list(BorrowInfo borrow);
    Integer updateBorrowInfo(BorrowInfo borrow);
    BorrowInfo queryBorrowByBookName(String bookName);
    Integer updateBookStateFree(String bookName);
    List<BorrowInfo> listByReaderName(String readerName);
    Integer updateReadCount(Integer count, String readerName);
    Integer countReadCount(String readerName);
}
