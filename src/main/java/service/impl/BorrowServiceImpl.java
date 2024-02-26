package service.impl;

import bean.BorrowInfo;
import dao.IBorrowDao;
import dao.impl.BorrowDaoImpl;
import service.IBorrowService;
import java.util.List;

public class BorrowServiceImpl implements IBorrowService {
    IBorrowDao borrowDao = new BorrowDaoImpl();

    @Override
    public List<BorrowInfo> list(BorrowInfo borrow) {
        return borrowDao.list(borrow);
    }
    @Override
    public Integer insertBorrowInfo(BorrowInfo borrow) {
        return borrowDao.insertBorrowInfo(borrow);
    }
    @Override
    public Integer updateBookStateBorrow(String bookName) {
        return borrowDao.updateBookStateBorrow(bookName);
    }
    @Override
    public Integer updateBorrowInfo(BorrowInfo borrow) {
        return borrowDao.updateBorrowInfo(borrow);
    }
    @Override
    public BorrowInfo queryBorrowByBookName(String bookName) {
        return borrowDao.queryBorrowByBookName(bookName);
    }
    @Override
    public Integer updateBookStateFree(String bookName) {
        return borrowDao.updateBookStateFree(bookName);
    }
    @Override
    public List<BorrowInfo> listByReaderName(String userName) {
        return borrowDao.listByReaderName(userName);
    }
    @Override
    public Integer updateReadCount(Integer count, String readerName) {
        return borrowDao.updateReadCount(count, readerName);
    }
    @Override
    public Integer countReadCount(String readerName) {
        return borrowDao.countReadCount(readerName);
    }
}
