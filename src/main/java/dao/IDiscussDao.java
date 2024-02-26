package dao;

import bean.DiscussInfo;

import java.util.List;

public interface IDiscussDao {
    /**
     * 显示评论信息
     */
    List<DiscussInfo> list(DiscussInfo discuss);

    /**
     * 发布评论
     */
    Integer saveComment(DiscussInfo discuss);

    /**
     * 删除评论
     */
    Integer deleteComment(String comment);

    /**
     *显示部分评论
     */
    List<DiscussInfo> partList(DiscussInfo discuss);
}
