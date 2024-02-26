package dao;

import bean.CommoityInfo;

import java.sql.SQLException;
import java.util.List;

public interface ICommodityDao {
    List<CommoityInfo> list(CommoityInfo book);
    Integer savebook(CommoityInfo book);
    Integer Buy(Integer id,String name) throws SQLException;

    Integer update(CommoityInfo commodity);
    Integer deleteByid(Integer id);
    CommoityInfo queryByid(Integer id);
}