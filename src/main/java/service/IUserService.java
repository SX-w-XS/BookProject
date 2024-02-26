package service;

import bean.PageInfo;
import bean.UserInfo;

import java.util.List;

public interface IUserService {
    UserInfo checkLoginInfo(String userName, String password);
    Integer userRegister(UserInfo user);
    Integer passwordUpdate(UserInfo user);
    String checkUserName(String userName);
    List<UserInfo> getUser(UserInfo user);
    Integer saveUser(UserInfo user,Integer integral);
    Integer deleteUserByName(String name);
    UserInfo queryById(Integer id);
    Integer updateUser(UserInfo user);
    List<UserInfo> searchUserByName(String keyWord);
    int size();
    List<UserInfo> queryAllByLimit(int offset, int limit);
    void selectUserListByPage(Integer currentPage, PageInfo<UserInfo> pageBean);
    List<Integer> countTime(Integer a);
    List<Integer> userTime(String username);
    List<String> getRecommendBook(String username);
}
