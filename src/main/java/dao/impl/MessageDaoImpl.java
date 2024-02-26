package dao.impl;

import bean.MessageInfo;
import dao.IMessageDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements IMessageDao {
    @Override
    public List<MessageInfo> GetAllMessage(MessageInfo message) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="select * from b_message order by N_Message_Id desc";
        List<MessageInfo> list = null;
        try {
            list=queryRunner.query(sql, new ResultSetHandler<List<MessageInfo>>() {
                @Override
                public List<MessageInfo> handle(ResultSet rs) throws SQLException {
                    List<MessageInfo> list=new ArrayList<>();
                    while(rs.next()) {
                        MessageInfo message = new MessageInfo();
                        message.setMessageId(rs.getInt("N_Message_Id"));
                        message.setMessageTitile(rs.getString("VC_Message_Titile"));
                        message.setMessageState(rs.getInt("N_Message_Stats"));
                        message.setMessageConnect(rs.getString("VC_Message_Connect"));
                        message.setMessageContent(rs.getString("VC_Message_Content"));
                        list.add(message);
                    }
                    return list;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Integer all(){
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="select count(*) from b_message ";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    int count=0;
                    if(rs.next()){
                        count=rs.getInt(1);
                    }
                    return count;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MessageInfo> GetSomeMessage(MessageInfo message) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        Integer limit=all();

        Integer offset=5;
        String sql="select * from b_message order by N_Message_Id  desc limit "+offset;
        List<MessageInfo> list = null;
        try {
            list=queryRunner.query(sql, new ResultSetHandler<List<MessageInfo>>() {
                @Override
                public List<MessageInfo> handle(ResultSet rs) throws SQLException {
                    List<MessageInfo>   list=new ArrayList<>();
                    while(rs.next()) {
                        MessageInfo message = new MessageInfo();
                        message.setMessageId(rs.getInt("N_Message_Id"));
                        message.setMessageTitile(rs.getString("VC_Message_Titile"));
                        message.setMessageState(rs.getInt("N_Message_Stats"));
                        message.setMessageConnect(rs.getString("VC_Message_Connect"));
                        message.setMessageContent(rs.getString("VC_Message_Content"));
                        list.add(message);
                    }
                    return list;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public Integer save(MessageInfo message) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="insert into b_message(VC_Message_Titile,N_Message_Stats,VC_Message_Connect,VC_Message_Content)values(?,?,?,?)";
        try {
            return   queryRunner.update(sql,message.getMessageTitile(),message.getMessageState(),message.getMessageConnect(),message.getMessageContent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer deleteMessage(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="delete from b_message where N_Message_Id= ?";
        try {
            return queryRunner.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static  void  main(String[] args){
        MessageDaoImpl dao=new MessageDaoImpl();
        System.out.println(dao.GetSomeMessage(null));
    }

}
