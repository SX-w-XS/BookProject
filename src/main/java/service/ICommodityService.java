package service;

import bean.CommoityInfo;

import java.sql.SQLException;
import java.util.List;

public interface ICommodityService {
    List<CommoityInfo> list(CommoityInfo commoityInfo);
    Integer savecommunity(CommoityInfo community);
    Integer updatecommunity(CommoityInfo community);

    Integer deleteByid(Integer id);
    CommoityInfo querybyId(Integer id);
    Integer Buy(Integer id,String name) throws SQLException;
}
