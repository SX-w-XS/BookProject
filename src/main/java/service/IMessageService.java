package service;

import bean.MessageInfo;

import java.util.List;

public interface IMessageService {
    List<MessageInfo> GetAllMessage(MessageInfo message);
    Integer saveMessage(MessageInfo message);
    Integer deleteMessage(Integer id);
    List<MessageInfo> GetSomeMessage(MessageInfo message);
}
