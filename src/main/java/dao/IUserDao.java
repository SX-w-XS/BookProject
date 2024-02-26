package dao;

import bean.PageInfo;
import bean.UserInfo;

import java.util.List;

public interface IUserDao {
    /**
     * 登录：用户名和密码检查
     * UserInfo 为null表示登录失败
     */
    UserInfo checkLoginInfo(String userName, String password);

    /**
     * 注册用户信息
     */
    Integer signUp(UserInfo user);

    /**
     * 检验用户是否已经存在
     */
    String checkUserName(String userName);

    /**
     * 修改密码：填写用户名和身份证号，检验用户信息正确后再修改密码
     */
    Integer passwordUpdate(UserInfo user);

    /**
     * 实现用户查询
     */
    List<UserInfo> list(UserInfo user);

    /**
     * 实现用户保存
     */
    Integer save(UserInfo user);

    /**
     * 实现用户删除
     */
    Integer delete(String name);

    /**
     * 单条用户查询
     */
    UserInfo queryById(Integer id);

    /**
     * 更新用户
     */
    Integer updateUser(UserInfo user);

    /**
     * 搜索账户
     */
    List<UserInfo> searchUserByName(String  userName);

    /**
     * 分页查询
     */
    int size();

    /**
     * 限制查询，显示一页的用户信息
     */
    List<UserInfo> queryAllByLimit(int offset, int limit);

    /**
     * 进行分页操作
     */
    void selectUserListByPage(Integer currentPage, PageInfo<UserInfo> pageBean);

    /**
     * 统计当前月份到之前6个月的借阅数（管理员端）
     */
    List<Integer> countTime(Integer a);

    /**
     * 统计当前月份到之前6个月的借阅数（用户端）
     */
    List<Integer> userTime(String username);
    List<Integer> getRecommend(String username);
    List<Integer> getAllRecommend(String username);
    List<String> getRecommendBook(String username);
}
