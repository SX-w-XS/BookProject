package dao.impl;

import bean.DiscussInfo;
import dao.IDiscussDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DelFlagE;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscussDaoImpl implements IDiscussDao {

    @Override
    public List<DiscussInfo> list(DiscussInfo discussInfo) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_discuss where N_Discuss_IsDeleted=? order by D_Discuss_ReleaseTime DESC";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<DiscussInfo>>() {
                @Override
                public List<DiscussInfo> handle(ResultSet rs) throws SQLException {
                    List<DiscussInfo> list = new ArrayList<>();
                    while (rs.next()){
                        DiscussInfo discuss = new DiscussInfo();
                        discuss.setId(rs.getInt("N_Discuss_Id"));
                        discuss.setContent(rs.getString("VC_Discuss_Content"));
                        discuss.setReleaseTime(rs.getString("D_Discuss_ReleaseTime"));
                        discuss.setName(rs.getString("VC_Discuss_Name"));
                        list.add(discuss);
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
    public Integer saveComment(DiscussInfo discuss) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into b_discuss(VC_Discuss_Content,D_Discuss_ReleaseTime,VC_Discuss_Name,N_Discuss_IsDeleted)values(?,?,?,?)";
        try {
            return queryRunner.update(sql,
                    discuss.getContent(),
                    discuss.getReleaseTime(),
                    discuss.getName(),
                    DelFlagE.NO.code
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer deleteComment(String id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_discuss set N_Discuss_IsDeleted=? where N_Discuss_Id=?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<DiscussInfo> partList(DiscussInfo discuss) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from b_discuss where N_Discuss_IsDeleted=? order by D_Discuss_ReleaseTime DESC";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<DiscussInfo>>() {
                @Override
                public List<DiscussInfo> handle(ResultSet rs) throws SQLException {
                    int i = 0;
                    List<DiscussInfo> list = new ArrayList<>();
                    while(rs.next()){
                        DiscussInfo discuss = new DiscussInfo();
                        discuss.setId(rs.getInt("N_Discuss_Id"));
                        discuss.setContent(rs.getString("VC_Discuss_Content"));
                        discuss.setReleaseTime(rs.getString("D_Discuss_ReleaseTime"));
                        discuss.setName(rs.getString("VC_Discuss_Name"));
                        if(i < 5){
                            list.add(discuss);
                            i++;
                        }
                    }
                    return list;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
