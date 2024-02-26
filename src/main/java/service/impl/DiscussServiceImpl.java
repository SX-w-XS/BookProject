package service.impl;

import bean.DiscussInfo;
import dao.IDiscussDao;
import dao.impl.DiscussDaoImpl;
import service.IDiscussService;

import java.util.List;

public class DiscussServiceImpl implements IDiscussService {
    IDiscussDao discussDao = new DiscussDaoImpl();
    @Override
    public List<DiscussInfo> list(DiscussInfo discuss) {
        return discussDao.list(discuss);
    }
    @Override
    public Integer saveComment(DiscussInfo discuss) {
        return discussDao.saveComment(discuss);
    }
    @Override
    public Integer deleteComment(String comment) {
        return discussDao.deleteComment(comment);
    }
    @Override
    public List<DiscussInfo> partList(DiscussInfo discuss) {
        return discussDao.partList(discuss);
    }
}
