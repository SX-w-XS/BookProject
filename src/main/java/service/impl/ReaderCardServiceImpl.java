package service.impl;

import bean.ReaderCardInfo;
import dao.IReaderCardDao;
import dao.impl.ReaderCardDaoImpl;
import service.IReaderCardService;

import java.util.List;

public class ReaderCardServiceImpl implements IReaderCardService {
    IReaderCardDao cardDao = new ReaderCardDaoImpl();

    @Override
    public List<ReaderCardInfo> list(ReaderCardInfo card) {
        return cardDao.list(card);
    }
    @Override
    public Integer saveReaderCard(ReaderCardInfo card) {
        return cardDao.saveReaderCard(card);
    }
    @Override
    public Integer updateReaderCard(ReaderCardInfo card) {
        return cardDao.updateReaderCard(card);
    }
    @Override
    public Integer deleteByID(Integer id) {
        return cardDao.deleteByID(id);
    }
    @Override
    public ReaderCardInfo queryByID(Integer id) {
        return cardDao.queryByID(id);
    }
    @Override
    public ReaderCardInfo queryByName(String readerName) {
        return cardDao.queryByName(readerName);
    }
    @Override
    public Integer updateIntegral(ReaderCardInfo card) {
        return cardDao.updateIntegral(card);
    }
}
