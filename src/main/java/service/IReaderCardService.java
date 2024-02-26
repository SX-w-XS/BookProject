package service;

import bean.ReaderCardInfo;

import java.util.List;

public interface IReaderCardService {
    List<ReaderCardInfo> list(ReaderCardInfo card);
    Integer saveReaderCard(ReaderCardInfo card);
    Integer updateReaderCard(ReaderCardInfo card);
    Integer deleteByID(Integer id);
    ReaderCardInfo queryByID(Integer id);
    ReaderCardInfo queryByName(String readerName);
    Integer updateIntegral(ReaderCardInfo card);
}
