package service.impl;

import bean.MessageInfo;
import dao.IMessageDao;
import dao.impl.MessageDaoImpl;
import service.IMessageService;

import java.util.List;

public class MessageServiceImpl implements IMessageService {
    IMessageDao messageDao = new MessageDaoImpl();
    @Override
    public List<MessageInfo> GetAllMessage(MessageInfo message) {
        return messageDao.GetAllMessage(message);
    }
    @Override
    public Integer saveMessage(MessageInfo message) {
        return messageDao.save(message);
    }
    @Override
    public Integer deleteMessage(Integer id) {
        return messageDao.deleteMessage(id);
    }
    @Override
    public List<MessageInfo> GetSomeMessage(MessageInfo message) {
        return messageDao.GetSomeMessage(null);
    }
}
