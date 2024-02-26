package dao.impl;

import bean.CommoityInfo;
import dao.ICommodityDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityDaoImpl implements ICommodityDao {
    String sql;
    QueryRunner queryRunner;
    @Override
    public List<CommoityInfo> list(CommoityInfo book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_commodity";
        List<CommoityInfo> list =null;
        try {
            list = queryRunner.query(sql, new ResultSetHandler<List<CommoityInfo>>() {
                @Override
                public List<CommoityInfo> handle(ResultSet rs) throws SQLException {
                    List<CommoityInfo> list = new ArrayList<>();
                    while(rs.next()){
                        CommoityInfo c = new CommoityInfo();
                        c.setCid(rs.getInt("N_Commodity_Id"));
                        c.setCname(rs.getString("VC_Commodity_Name"));
                        c.setCprice(rs.getInt("N_Commodity_Price"));
                        c.setCcount(rs.getInt("N_Commodity_Count"));
                        c.setCnumber(rs.getInt("N_Commodity_Number"));
                        c.setCpudate(rs.getDate("D_Commodity_Pudate"));
                        c.setCintroduction(rs.getNString("VC_Commodity_Introduction"));
                        c.setCstate(rs.getInt("N_Commodity_States"));
                        list.add(c);
                    }
                    return list;
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    @Override
    public Integer savebook(CommoityInfo book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="insert into b_commodity(VC_Commodity_Name,VC_Commodity_Introduction,D_Commodity_Pudate,N_Commodity_Price,N_Commodity_Number,N_Commodity_States)values(?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, book.getCname(),
                    book.getCintroduction(),
                    book.getCpudate(),
                    book.getCprice(),
                    book.getCnumber(),
                    book.getCstate());
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer Buy(Integer id, String name) throws SQLException {
        queryRunner = MyDBUtils.getQueryRunner();
        final int[] i = {0}; //保存用户的积分
        final int[] n = {0}; //保存商品数量
        final int[] p = {0}; //保存商品价格
        final int[] c = {0}; //保存商品交易数
        final int[] s = {0}; //保存状态标识
        sql ="select * from b_readcard where VC_ReadCard_ReadName = ?";
        queryRunner.query(sql, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException{
                rs.next();
                i[0] = rs.getInt("N_Card_Integral");
                return null;
            }
        },name);

        String sql1 = "select * from b_commodity where N_Commodity_Id = ?";
        queryRunner.query(sql1, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException{
                rs.next();
                n[0] = rs.getInt("N_Commodity_Number");
                p[0] = rs.getInt("N_Commodity_Price");
                c[0] = rs.getInt("N_Commodity_Count");
                return null;
            }
        },id);

        System.out.println("用户积分"+i[0]);
        System.out.println("商品数量"+n[0]);
        System.out.println("价格"+p[0]);
        System.out.println("交易次数"+c[0]);

        if((n[0]-1)<=0){
            s[0] = 0;
        }
        else{
            s[0] = 1;
        }

        String sql2 = "update b_commodity set N_Commodity_Number = ?,N_Commodity_Count = ?,N_Commodity_States = ? where N_Commodity_Id = ?";
        try {
            if(n[0]>0&&(i[0]-p[0]>=0)){
                queryRunner.update(sql2,n[0]-1, c[0]+1, s[0], id);
            }else{
                return -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        String sql3 ="update b_readcard set N_Card_Integral = ? where VC_ReadCard_ReadName = ?";
        try {
            if((i[0]-p[0])>=0){
                System.out.println(i[0]-p[0]);
            return queryRunner.update(sql3,i[0]-p[0], name);
            }
            else{
                return -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public Integer update(CommoityInfo commodity) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql="update b_commodity set VC_Commodity_Name = ?,N_Commodity_Price = ?,N_Commodity_Number = ?,D_Commodity_Pudate = ?,VC_Commodity_Introduction = ?, N_Commodity_States = ? where N_Commodity_Id = ?";
        try {
            return queryRunner.update(sql, commodity.getCname(),
                    commodity.getCprice(),
                    commodity.getCnumber(),
                    commodity.getCpudate(),
                    commodity.getCintroduction(),
                    commodity.getCstate(),
                    commodity.getCid());
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public Integer deleteByid(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "delete from b_commodity where N_Commodity_Id = ?";
        try{
            return queryRunner.update(sql,id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public CommoityInfo queryByid(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql ="select * from b_commodity where  N_Commodity_Id = ?";
        List<CommoityInfo> list =null;
        try {
            return queryRunner.query(sql, new ResultSetHandler<CommoityInfo>() {
                @Override
                public CommoityInfo handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        CommoityInfo c = new CommoityInfo();
                        c.setCid(rs.getInt("N_Commodity_Id"));
                        c.setCname(rs.getString("VC_Commodity_Name"));
                        c.setCprice(rs.getInt("N_Commodity_Price"));
                        c.setCnumber(rs.getInt("N_Commodity_Number"));
                        c.setCpudate(rs.getDate("D_Commodity_Pudate"));
                        c.setCintroduction(rs.getNString("VC_Commodity_Introduction"));
                        c.setCstate(rs.getInt("N_Commodity_States"));
                        return c;
                    }
                    return null;
                }
            },id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
