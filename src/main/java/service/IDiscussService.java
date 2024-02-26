package service;

import bean.DiscussInfo;

import java.util.List;

public interface IDiscussService {
    List<DiscussInfo> list(DiscussInfo discuss);
    Integer saveComment(DiscussInfo discuss);
    Integer deleteComment(String comment);
    List<DiscussInfo> partList(DiscussInfo discuss);
}
