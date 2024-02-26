package dao.impl;

import bean.ReaderCardInfo;
import dao.IReaderCardDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DelFlagE;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderCardDaoImpl implements IReaderCardDao {

    @Override
    public List<ReaderCardInfo> list(ReaderCardInfo card) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_readcard where N_Card_IsDeleted=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<ReaderCardInfo>>() {
                @Override
                public List<ReaderCardInfo> handle(ResultSet rs) throws SQLException {
                    List<ReaderCardInfo> list = new ArrayList<>();
                    while (rs.next()){
                        ReaderCardInfo card = new ReaderCardInfo();
                        card.setId(rs.getInt("N_ReadCard_Id"));
                        card.setReaderName(rs.getString("VC_ReadCard_ReadName"));
                        card.setCardState(rs.getInt("N_ReadCard_State"));
                        card.setReadCount(rs.getInt("N_Reader_Count"));
                        card.setReadTime(rs.getInt("T_Reader_Time"));
                        card.setIsDeleted(rs.getInt("N_Card_IsDeleted"));
                        card.setIntegral(rs.getInt("N_Card_Integral"));
                        list.add(card);
                    }
                    return list;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer saveReaderCard(ReaderCardInfo card) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into b_readcard(VC_ReadCard_ReadName,N_Card_Integral)values(?,?)";
        try {
            return queryRunner.update(sql,
                    card.getReaderName(),
                    card.getIntegral()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public Integer updateReaderCard(ReaderCardInfo card) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_readcard set N_ReadCard_State=?,T_Reader_Time=? where N_ReadCard_Id=?";
        try {
            return queryRunner.update(sql,
                    card.getCardState(),
                    card.getReadTime(),
                    card.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public Integer deleteByID(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_readcard set N_Card_IsDeleted=? where N_ReadCard_Id=?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public ReaderCardInfo queryByID(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_readcard where N_Card_IsDeleted=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<ReaderCardInfo>() {
                @Override
                public ReaderCardInfo handle(ResultSet rs) throws SQLException {
                    if (rs.next()){
                        ReaderCardInfo card = new ReaderCardInfo();
                        card.setId(rs.getInt("N_ReadCard_Id"));
                        card.setReaderName(rs.getString("VC_ReadCard_ReadName"));
                        card.setCardState(rs.getInt("N_ReadCard_State"));
                        card.setReadCount(rs.getInt("N_Reader_Count"));
                        card.setReadTime(rs.getInt("T_Reader_Time"));
                        card.setIsDeleted(rs.getInt("N_Card_IsDeleted"));
                        card.setIntegral(rs.getInt("N_Card_Integral"));
                        return card;
                    }
                    return null;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ReaderCardInfo queryByName(String readerName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_readcard where N_Card_IsDeleted=? and VC_ReadCard_ReadName=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<ReaderCardInfo>() {
                @Override
                public ReaderCardInfo handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        ReaderCardInfo card = new ReaderCardInfo();
                        card.setId(rs.getInt("N_ReadCard_Id"));
                        card.setReaderName(rs.getString("VC_ReadCard_ReadName"));
                        card.setCardState(rs.getInt("N_ReadCard_State"));
                        card.setReadCount(rs.getInt("N_Reader_Count"));
                        card.setReadTime(rs.getInt("T_Reader_Time"));
                        card.setIsDeleted(rs.getInt("N_Card_IsDeleted"));
                        card.setIntegral(rs.getInt("N_Card_Integral"));
                        return card;
                    }
                    return null;
                }
            }, DelFlagE.NO.code, readerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateIntegral(ReaderCardInfo card) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_readcard set N_Card_Integral=? where VC_ReadCard_ReadName=?";
        try {
            return queryRunner.update(sql,card.getIntegral(),card.getReaderName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
