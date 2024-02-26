package dao;

import bean.ReaderCardInfo;

import java.util.List;

public interface IReaderCardDao {
    /**
     * 显示借书卡信息列表
     */
    List<ReaderCardInfo> list(ReaderCardInfo card);

    /**
     *
     */
    Integer saveReaderCard(ReaderCardInfo card);

    /**
     * 更新修改的借书卡信息
     */
    Integer updateReaderCard(ReaderCardInfo card);

    /**
     * 根据借书卡ID删除借书卡信息
     */
    Integer deleteByID(Integer id);

    /**
     * 根据借书卡ID查询借书卡信息
     */
    ReaderCardInfo queryByID(Integer id);

    /**
     * 根据用户名查询借书卡信息
     */
    ReaderCardInfo queryByName(String userName);

    /**
     * 更新借书卡积分
     */
    Integer updateIntegral(ReaderCardInfo card);
}
