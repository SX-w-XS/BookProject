package dao.impl;

import CF.TestUserBasedCF;
import bean.PageInfo;
import bean.UserInfo;
import dao.IUserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utils.DelFlagE;
import utils.MyDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public UserInfo checkLoginInfo(String userName, String password) {
        if(userName.equals("Admin1") || userName.equals("Admin2")){
            QueryRunner queryRunner = MyDBUtils.getQueryRunner();
            String sql = "select * from b_manager where N_manager_name=? and N_manager_password=?";
            try {
                return queryRunner.query(sql, new ResultSetHandler<UserInfo>() {
                    @Override
                    public UserInfo handle(ResultSet rs) throws SQLException {
                        if (rs.next()) {
                            UserInfo user = new UserInfo();
                            user.setUserName(rs.getString("N_manager_name"));
                            user.setPassword(rs.getString("N_manager_password"));
                            return user;
                        }
                        return null;
                    }
                }, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            QueryRunner queryRunner = MyDBUtils.getQueryRunner();
            String sql = "select * from b_reader where VC_Reader_Name=? and VC_Reader_Password=?";
            try {
                return queryRunner.query(sql, new ResultSetHandler<UserInfo>() {
                    @Override
                    public UserInfo handle(ResultSet rs) throws SQLException {
                        if (rs.next()) {
                            UserInfo user = new UserInfo();
                            user.setUserName(rs.getString("VC_Reader_Name"));
                            user.setId(rs.getInt("N_Reader_Id"));
                            user.setUserSex(rs.getString("VC_Reader_Sex"));
                            user.setBirthday(rs.getString("D_Reader_Birthday"));
                            user.setIdentify(rs.getString("VC_Reader_Identify"));
                            user.setTel(rs.getString("VC_Reader_Tel"));
                            user.setEmail(rs.getString("VC_Reader_Email"));
                            user.setPassword(rs.getString("VC_Reader_Password"));
                            user.setIsDeleted(rs.getInt("N_Reader_IsDeleted"));
                            return user;
                        }
                        return null;
                    }
                }, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public Integer signUp(UserInfo user) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into b_reader(VC_Reader_Name,VC_Reader_Sex,D_Reader_Birthday,VC_Reader_Identify,VC_Reader_Tel,VC_Reader_Email,VC_Reader_Password)values(?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql,user.getUserName()
                    ,user.getUserSex()
                    ,user.getBirthday()
                    ,user.getIdentify()
                    ,user.getTel()
                    ,user.getEmail()
                    ,user.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Integer passwordUpdate(UserInfo user){
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_reader set VC_Reader_Password=? where VC_Reader_Name=? and VC_Reader_Identify=?";
        try {
            return queryRunner.update(sql, user.getPassword(), user.getUserName(), user.getIdentify());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public String checkUserName(String userName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select count(1) from b_reader where VC_Reader_Name=?";
        try {
            int count = queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);
                    return count;
                }
            },userName);
            return count == 0 ? "success" : "error";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "error";
    }
    @Override
    public List<UserInfo> list(UserInfo user) {
        int offset=0;
        int limit=3;
        QueryRunner queryRunner= MyDBUtils.getQueryRunner();
        String sql="select * from b_reader where  N_Reader_IsDeleted = ? ";
        List<UserInfo> list=null;
        try {
            list=queryRunner.query(sql, new ResultSetHandler<List<UserInfo>>() {
                @Override
                public List<UserInfo> handle(ResultSet resultSet) throws SQLException {
                    List<UserInfo> list =new ArrayList<>();
                    while(resultSet.next()){
                        UserInfo user =new UserInfo();
                        user.setId(resultSet.getInt("N_Reader_Id"));
                        user.setUserName(resultSet.getString("VC_Reader_Name"));
                        user.setUserSex(resultSet.getString("VC_Reader_Sex"));
                        user.setBirthday(String.valueOf(resultSet.getDate("D_Reader_Birthday")));
                        user.setIdentify(resultSet.getString("VC_reader_Identify"));
                        user.setTel(resultSet.getString("VC_Reader_Tel"));
                        user.setEmail(resultSet.getString("VC_Reader_Email"));
                        user.setPassword(resultSet.getString("VC_Reader_Password"));
                        user.setIntegral(resultSet.getInt("N_Reader_Integral"));
                        user.setIsDeleted(resultSet.getInt("N_Reader_IsDeleted"));

                        list.add(user);
                    }
                    return list;
                }
            },DelFlagE.NO.code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public Integer save(UserInfo user){
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql ="insert into b_reader(VC_Reader_Name,VC_Reader_Password,VC_Reader_Tel,VC_Reader_Email,D_Reader_Birthday,VC_Reader_Identify,VC_Reader_Sex,N_Reader_Integral)values(?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql,
                    user.getUserName(),
                    user.getPassword(),
                    user.getTel(),
                    user.getEmail(),
                    user.getBirthday(),
                    user.getIdentify(),
                    user.getUserSex(),
                    user.getIntegral());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Integer delete(String name) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="update b_reader set N_Reader_IsDeleted = ? where VC_Reader_Name= ?";
        try {
            return queryRunner.update(sql, DelFlagE.YES.code,name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public UserInfo queryById(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql ="select * from b_reader where N_Reader_Id = ? and  N_Reader_IsDeleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<UserInfo>() {

                @Override
                public UserInfo handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        UserInfo user = new UserInfo();
                        user.setId(id);
                        user.setIsDeleted(rs.getInt("N_Reader_IsDeleted"));
                        user.setUserName(rs.getString("VC_Reader_Name"));
                        user.setPassword(rs.getString("VC_Reader_Password"));
                        user.setTel(rs.getString("VC_Reader_Tel"));
                        user.setEmail(rs.getString("VC_Reader_Email"));
                        user.setBirthday(String.valueOf(rs.getDate("D_Reader_Birthday")));
                        user.setIdentify(rs.getString("VC_reader_Identify"));
                        user.setUserSex(rs.getString("VC_Reader_Sex"));
                        user.setIntegral(rs.getInt("N_Reader_Integral"));
                        return user;
                    }
                    return null;
                }
            },id,DelFlagE.NO.code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Integer updateUser(UserInfo user) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update b_reader set VC_Reader_Name = ?,VC_Reader_Password=?,VC_Reader_Tel=?,VC_Reader_Email=?,VC_Reader_Sex=?,D_Reader_Birthday =?,VC_Reader_Identify=? ,N_Reader_Integral=? where N_Reader_Id = ?";
        try {
            return queryRunner.update(sql,
                    user.getUserName(),
                    user.getPassword(),
                    user.getTel(),
                    user.getEmail(),
                    user.getUserSex(),
                    user.getBirthday(),
                    user.getIdentify(),
                    user.getIntegral(),
                    user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public  List<UserInfo> searchUserByName(String userName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        List<UserInfo> list=null;
        String sql ="select * from b_reader where N_Reader_IsDeleted = ? and VC_Reader_Name like ? ";
        try {
            list=queryRunner.query(sql, new ResultSetHandler<List<UserInfo>>() {
                @Override
                public List<UserInfo> handle(ResultSet rs) throws SQLException {
                    List<UserInfo> list =new ArrayList<>();
                    while(rs.next()){
                        UserInfo user = new UserInfo();
                        user.setIsDeleted(rs.getInt("N_Reader_IsDeleted"));
                        user.setUserName(rs.getString("VC_Reader_Name"));
                        user.setPassword(rs.getString("VC_Reader_Password"));
                        user.setTel(rs.getString("VC_Reader_Tel"));
                        user.setEmail(rs.getString("VC_Reader_Email"));
                        user.setBirthday(String.valueOf(rs.getDate("D_Reader_Birthday")));
                        user.setIdentify(rs.getString("VC_reader_Identify"));
                        user.setUserSex(rs.getString("VC_Reader_Sex"));
                        user.setIntegral(rs.getInt("N_Reader_Integral"));
                        list.add(user);
                    }
                    return list;
                }
            },DelFlagE.NO.code,userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public int size() {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();

        String sql="select count(*) from b_reader where N_Reader_IsDeleted = ? ";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>(){
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    int count=0;
                    if(rs.next()){
                        count=rs.getInt(1);}
                    return count;
                }
            },DelFlagE.NO.code);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<UserInfo> queryAllByLimit(int offset, int limit) {
        QueryRunner queryRunner= MyDBUtils.getQueryRunner();
        String sql="select * from b_reader where  N_Reader_IsDeleted = ? limit "+offset+","+limit;;
        List<UserInfo> list=null;
        try {
            list=queryRunner.query(sql, new ResultSetHandler<List<UserInfo>>() {
                @Override
                public List<UserInfo> handle(ResultSet rs) throws SQLException {
                    List<UserInfo> list =new ArrayList<>();
                    while(rs.next()){
                        UserInfo user = new UserInfo();
                        user.setId(rs.getInt("N_Reader_Id"));
                        user.setIsDeleted(rs.getInt("N_Reader_IsDeleted"));
                        user.setUserName(rs.getString("VC_Reader_Name"));
                        user.setPassword(rs.getString("VC_Reader_Password"));
                        user.setTel(rs.getString("VC_Reader_Tel"));
                        user.setEmail(rs.getString("VC_Reader_Email"));
                        user.setBirthday(String.valueOf(rs.getDate("D_Reader_Birthday")));
                        user.setIdentify(rs.getString("VC_Reader_Identify"));
                        user.setUserSex(rs.getString("VC_Reader_Sex"));
                        user.setIntegral(rs.getInt("N_Reader_Integral"));
                        list.add(user);
                    }
                    return list;
                }
            },DelFlagE.NO.code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public void selectUserListByPage(Integer currentPage, PageInfo<UserInfo> pageBean) {
        //当前页面
        pageBean.setCurrentPage(currentPage);
        //总记录
        Integer totalSize=size();
        pageBean.setTotalSize(totalSize);
        //每页最大记录
        Integer pageSize=5;
        pageBean.setPageSize(pageSize);
        //总页数（有余数+1，没余数整页）
        Integer totalPages = null;
        if (totalSize != null) {
            totalPages = (totalSize%pageSize==0) ? (totalSize/pageSize) : (totalSize/pageSize+1);
        }
        if(totalPages != null) {
            pageBean.setTotalPage(totalPages);
        }
        List<UserInfo> list=queryAllByLimit(pageSize*(currentPage-1), pageSize);
        pageBean.setList(list);
    }
    @Override
    public List<Integer> countTime(Integer a) {

        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        List<Integer> a1=new ArrayList<>();
        String sql="select D_Borrow_Botime from b_borrow";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Integer>>() {

                @Override
                public List<Integer> handle(ResultSet resultSet) throws SQLException {
                    List<Integer> a2=new ArrayList<>();
                    Integer count1=0;
                    Integer count2=0;
                    Integer count3=0;
                    Integer count4=0;
                    Integer count5=0;
                    Integer count6=0;
                    while(resultSet.next()){
                        Date date=resultSet.getDate("D_Borrow_Botime");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY");
                        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM");
                        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY");
                        SimpleDateFormat dateFormat1=new SimpleDateFormat("MM");
                        Integer mtime=Integer.valueOf(dateFormat1.format(date));
                        Integer ytime=Integer.valueOf(dateFormat.format(date));
                        Integer mtime1=Integer.valueOf(dateTimeFormatter1.format(localDateTime));
                        Integer ytime1=Integer.valueOf(dateTimeFormatter.format(localDateTime));
                        String nowTime1=ytime1+"-"+mtime1;
                        String XTime=ytime+"-"+mtime;
                        String nowTime2=null;
                        String nowTime3=null;
                        String nowTime4=null;
                        String nowTime5=null;
                        String nowTime6=null;
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                             nowTime2=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=12;
                            mtime=12;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                            nowTime2=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                             nowTime3=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=11;
                            mtime=11;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                             nowTime3=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                             nowTime4=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=10;
                            mtime=10;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                             nowTime4=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                             nowTime5=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=9;
                            mtime=9;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                             nowTime5=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                             nowTime6=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=8;
                            mtime=8;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                             nowTime6=ytime1+"-"+mtime1;
                        }
                        if(nowTime1.equals(XTime)){
                           count1+=1;
                        }
                        else if(nowTime2.equals(XTime)){
                            count2+=1;
                        }else if(nowTime3.equals(XTime)){
                            count3+=1;
                        }else if(nowTime4.equals(XTime)){
                            count4+=1;
                        }else if(nowTime5.equals(XTime)){
                            count5+=1;
                        }else if(nowTime6.equals(XTime)){
                            count6+=1;
                        }
                    }
                    a2.add(count6);
                    a2.add(count5);
                    a2.add(count4);
                    a2.add(count3);
                    a2.add(count2);
                    a2.add(count1);
                    return  a2;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Integer> userTime(String username) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        List<Integer> a1=new ArrayList<>();
        String sql="select D_Borrow_Botime from b_borrow where VC_Borrow_ReaderName = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Integer>>() {

                @Override
                public List<Integer> handle(ResultSet resultSet) throws SQLException {
                    List<Integer> a2=new ArrayList<>();
                    Integer count1=0;
                    Integer count2=0;
                    Integer count3=0;
                    Integer count4=0;
                    Integer count5=0;
                    Integer count6=0;
                    while(resultSet.next()){
                        Date date=resultSet.getDate("D_Borrow_Botime");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY");
                        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM");
                        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY");
                        SimpleDateFormat dateFormat1=new SimpleDateFormat("MM");
                        Integer mtime=Integer.valueOf(dateFormat1.format(date));
                        Integer ytime=Integer.valueOf(dateFormat.format(date));
                        Integer mtime1=Integer.valueOf(dateTimeFormatter1.format(localDateTime));
                        Integer ytime1=Integer.valueOf(dateTimeFormatter.format(localDateTime));
                        String nowTime1=ytime1+"-"+mtime1;
                        String XTime=ytime+"-"+mtime;
                        String nowTime2=null;
                        String nowTime3=null;
                        String nowTime4=null;
                        String nowTime5=null;
                        String nowTime6=null;
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                            nowTime2=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=12;
                            mtime=12;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                            nowTime2=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                            nowTime3=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=11;
                            mtime=11;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                            nowTime3=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                            nowTime4=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=10;
                            mtime=10;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                            nowTime4=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                            nowTime5=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=9;
                            mtime=9;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                            nowTime5=ytime1+"-"+mtime1;
                        }
                        mtime1=mtime1-1;
                        mtime=mtime-1;
                        if(mtime1>=1){
                            nowTime6=ytime1+"-"+mtime1;
                        }
                        else{
                            mtime1=8;
                            mtime=8;
                            ytime1=ytime1-1;
                            ytime=ytime-1;
                            nowTime6=ytime1+"-"+mtime1;
                        }
                        if(nowTime1.equals(XTime)){
                            count1+=1;
                        }else if(nowTime2.equals(XTime)){
                            count2+=1;
                        }else if(nowTime3.equals(XTime)){
                            count3+=1;
                        }else if(nowTime4.equals(XTime)){
                            count4+=1;
                        }else if(nowTime5.equals(XTime)){
                            count5+=1;
                        }else if(nowTime6.equals(XTime)){
                            count6+=1;
                        }
                    }
                    a2.add(count6);
                    a2.add(count5);
                    a2.add(count4);
                    a2.add(count3);
                    a2.add(count2);
                    a2.add(count1);
                    return a2;
                }
            },username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Integer> getRecommend(String username) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="select N_Borrow_Type from b_borrow where VC_Borrow_ReaderName = ?";

        Integer[] a=new Integer[6];
        for(int i=0;i<=5;i++){
            a[i]=0;
        }

        try {
         return    queryRunner.query(sql, new ResultSetHandler<List<Integer>>() {
                @Override
                public List<Integer> handle(ResultSet rs) throws SQLException {
                    List<Integer> list=new ArrayList<>();
                    while (rs.next()){
                        Integer count=null;
                        count=rs.getInt("N_Borrow_Type");
                        if(count==0){
                            a[0]+=1;
                        } else if (count==1) {
                            a[1]+=1;
                        } else if (count==2) {
                            a[2]+=1;
                        } else if (count==3) {
                            a[3]+=1;
                        } else if (count==4) {
                            a[4]+=1;
                        } else if (count==5) {
                            a[5]+=1;
                        }
                    }
                    list.add(a[0]);
                    list.add(a[1]);
                    list.add(a[2]);
                    list.add(a[3]);
                    list.add(a[4]);
                    list.add(a[5]);
                    return list;
                }
            },username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllRecommend(String username) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="select N_Borrow_Type from b_borrow";

        Integer[] a=new Integer[6];
        for(int i=0;i<=5;i++){
            a[i]=0;
        }

        try {
            return    queryRunner.query(sql, new ResultSetHandler<List<Integer>>() {
                @Override
                public List<Integer> handle(ResultSet rs) throws SQLException {
                    List<Integer> list=new ArrayList<>();
                    while (rs.next()){
                        Integer count=null;
                        count=rs.getInt("N_Borrow_Type");
                        if(count==0){
                            a[0]+=1;
                        } else if (count==1) {
                            a[1]+=1;
                        } else if (count==2) {
                            a[2]+=1;
                        } else if (count==3) {
                            a[3]+=1;
                        } else if (count==4) {
                            a[4]+=1;
                        } else if (count==5) {
                            a[5]+=1;
                        }
                    }
                    list.add(a[0]);
                    list.add(a[1]);
                    list.add(a[2]);
                    list.add(a[3]);
                    list.add(a[4]);
                    list.add(a[5]);
                    return list;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getRecommendBook(String username) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        List<String> book=new ArrayList<>();

        TestUserBasedCF cf=new TestUserBasedCF();
        List<Integer> sort=new ArrayList<>();
        sort=cf.getRecommendSort(username);
        Integer a,b;
        a=sort.get(0);
        b=sort.get(1);
        String sql="select VC_Book_Name from b_books where N_Book_Type = ?";
        try {
             queryRunner.query(sql, new ResultSetHandler<List<String>>() {
                @Override
                public List<String> handle(ResultSet rs) throws SQLException {
                    String name=null;

                     while (rs.next()){
                         name=rs.getString("VC_Book_Name");
                         book.add(name);
                     }
                    return book;
                }
            },a);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      String sql1="select VC_Book_Name from b_books where N_Book_Type = ?";
        try {
           return queryRunner.query(sql1, new ResultSetHandler<List<String>>() {
                @Override
                public List<String> handle(ResultSet rs) throws SQLException {
                    String name=null;
                    while(rs.next()){
                       name= rs.getString("VC_Book_Name");
                        book.add(name);
                    }
                    return book;
                }
            },b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static  void main(String[] args){
        UserDaoImpl dao = new UserDaoImpl();
        dao.delete("1231");
        System.out.println(dao.delete("1231"));
    }
}
