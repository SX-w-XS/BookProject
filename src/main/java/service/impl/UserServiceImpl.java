package service.impl;

import bean.PageInfo;
import bean.ReaderCardInfo;
import bean.UserInfo;
import dao.IReaderCardDao;
import dao.IUserDao;
import dao.impl.ReaderCardDaoImpl;
import dao.impl.UserDaoImpl;
import service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();
    IReaderCardDao cardDao = new ReaderCardDaoImpl();
    @Override
    public UserInfo checkLoginInfo(String userName, String password) {
        return userDao.checkLoginInfo(userName, password);
    }
    @Override
    public Integer userRegister(UserInfo user) {
        //注册用户时，同时创建借书卡
        ReaderCardInfo card = new ReaderCardInfo();
        card.setReaderName(user.getUserName());
        cardDao.saveReaderCard(card);//调用注册借书卡方法
        return userDao.signUp(user);
    }
    @Override
    public String checkUserName(String userName) {
        return userDao.checkUserName(userName);
    }
    @Override
    public Integer passwordUpdate(UserInfo user) {
        return userDao.passwordUpdate(user);
    }
    @Override
    public List<UserInfo> getUser(UserInfo user) {
        return userDao.list(user);
    }
    @Override
    public Integer saveUser(UserInfo user, Integer integral) {
        //注册用户时，同时创建借书卡
        ReaderCardInfo card = new ReaderCardInfo();
        card.setReaderName(user.getUserName());
        card.setIntegral(integral);
        cardDao.saveReaderCard(card);
        return userDao.save(user);
    }
    @Override
    public Integer deleteUserByName(String name) {
        return  userDao.delete(name);
    }
    @Override
    public UserInfo queryById(Integer id) {
        return userDao.queryById(id);
    }
    @Override
    public Integer updateUser(UserInfo user) {
        return userDao.updateUser(user);
    }
    @Override
    public List<UserInfo> searchUserByName(String keyWord) {
        return  userDao.searchUserByName(keyWord);
    }
    @Override
    public int size() {
        return userDao.size();
    }
    @Override
    public List<UserInfo> queryAllByLimit(int offset, int limit) {
        return userDao.queryAllByLimit(offset,limit);
    }
    @Override
    public void selectUserListByPage(Integer currentPage, PageInfo<UserInfo> pageInfo) {
        userDao.selectUserListByPage(currentPage,pageInfo);
    }
    @Override
    public List<Integer> countTime(Integer a) {
        return userDao.countTime(a);
    }
    @Override
    public List<Integer> userTime(String username) {
        return userDao.userTime(username);
    }

    @Override
    public List<String> getRecommendBook(String username) {
        return userDao.getRecommendBook(username);
    }
}
