package service.impl;

import bean.CommoityInfo;
import dao.impl.CommodityDaoImpl;
import service.ICommodityService;
import java.sql.SQLException;
import java.util.List;

public class CommodityServiceImpl implements ICommodityService {
    private CommodityDaoImpl commodityDao = new CommodityDaoImpl();

    @Override
    public List<CommoityInfo> list(CommoityInfo community) {
        return commodityDao.list(community);
    }
    @Override
    public Integer savecommunity(CommoityInfo community) {
        return commodityDao.savebook(community);
    }
    @Override

    public Integer updatecommunity(CommoityInfo community) {
        return commodityDao.update(community);
    }
    @Override
    public Integer deleteByid(Integer id) {
        return commodityDao.deleteByid(id);
    }
    @Override
    public CommoityInfo querybyId(Integer id) {
        return commodityDao.queryByid(id);
    }
    @Override
    public Integer Buy(Integer id, String name) throws SQLException{
        return commodityDao.Buy(id,name);
    }
}
