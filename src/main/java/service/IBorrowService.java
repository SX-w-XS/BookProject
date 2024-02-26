package service;

import bean.BorrowInfo;

import java.util.List;

public interface IBorrowService {
    List<BorrowInfo> list(BorrowInfo borrow);
    Integer insertBorrowInfo(BorrowInfo borrow);
    Integer updateBookStateBorrow(String bookName);
    Integer updateBorrowInfo(BorrowInfo borrow);
    BorrowInfo queryBorrowByBookName(String bookName);
    Integer updateBookStateFree(String bookName);
    List<BorrowInfo> listByReaderName(String userName);
    Integer updateReadCount(Integer count, String readerName);
    Integer countReadCount(String readerName);
}
