package dao;

import bean.MessageInfo;

import java.util.List;

public interface IMessageDao {
    List<MessageInfo> GetAllMessage(MessageInfo message);
    List<MessageInfo> GetSomeMessage(MessageInfo message);
    Integer save(MessageInfo message);
    Integer deleteMessage(Integer id);
}
